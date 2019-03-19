package com.senpure.chat.game.configuration;

import com.senpure.io.IOServerProperties;
import com.senpure.io.RealityMessageHandlerUtil;
import com.senpure.io.bean.HandleMessage;
import com.senpure.io.event.EventHelper;
import com.senpure.io.handler.RealityMessageHandler;
import com.senpure.io.message.SCRegServerHandleMessageMessage;
import com.senpure.io.message.Server2GatewayMessage;
import com.senpure.io.server.GatewayManager;
import com.senpure.io.server.RealityMessageExecuter;
import com.senpure.io.server.RealityServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * GameConfigration
 *
 * @author senpure
 * @time 2018-12-17 18:02:46
 */
//@Configuration
//@EnableConfigurationProperties(IOServerProperties.class)
public class GameConfiguration {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IOServerProperties properties;
    private List<RealityServer> servers = new ArrayList<>();

    @Autowired
    private GatewayManager gatewayManager;

   // @PostConstruct
    public void init() {
        EventHelper.setService(Executors.newSingleThreadScheduledExecutor());
    }




    //@Bean
    public RealityMessageExecuter realityMessageExecuter() {
        RealityMessageExecuter realityMessageExecuter = new RealityMessageExecuter();
        return realityMessageExecuter;
    }

   // @PostConstruct
    public void start() throws SSLException, CertificateException {

//        String[] temp = properties.getGatewayAddress().split(",");
//        for (int i = 0; i < temp.length; i++) {
//            String[] oneAddressTemp = temp[i].split(":");
//            String host = oneAddressTemp[0];
//            int port = Integer.parseInt(oneAddressTemp[1]);
//            RealityServer realityServer = new RealityServer();
//           // realityServer.setProperties(properties);
//            realityServer.setServerName("chatGameServer");
//            realityServer.setGatewayManager(gatewayManager);
//            realityServer.setMessageExecuter(realityMessageExecuter());
//
//            boolean start = realityServer.start(host, port);
//            if (start) {
//                servers.add(realityServer);
//                // Channel channel=realityServer.getChannel();
//
//            }

     //   }
    }

   // @PreDestroy
    public void destroy() {
        for (RealityServer server : servers) {
            server.destroy();
        }
    }

   // @Bean
    public RegServerToGateway regServerToGateway() {
        return new RegServerToGateway();
    }

    class RegServerToGateway implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments applicationArguments) throws Exception {
            List<Integer> ids = RealityMessageHandlerUtil.getHandlerMessageIds();

            List<HandleMessage> handleMessages = new ArrayList<>();
            for (Integer id : ids) {
                HandleMessage handleMessage = new HandleMessage();
                handleMessage.setHandleMessageId(id);
                RealityMessageHandler handler = RealityMessageHandlerUtil.getHandler(id);
                handleMessage.setDirect(handler.direct());
                handleMessage.setServerShare(handler.serverShare());
                handleMessage.setMessageClasses(RealityMessageHandlerUtil.getEmptyMessage(id).getClass().getName());
                handleMessages.add(handleMessage);
            }
            for (RealityServer server : servers) {
                SCRegServerHandleMessageMessage message = new SCRegServerHandleMessageMessage();
                message.setServerName(server.getServerName());
                message.setReadableServerName(server.getReadableServerName());
                InetSocketAddress address = (InetSocketAddress) server.getChannel().localAddress();
              //  String ipAndPort = address.getAddress().getHostAddress() + ":" + server.getFirstPort();
              //  message.setIpAndFirstPort(ipAndPort);
                message.setMessages(handleMessages);
                Server2GatewayMessage gatewayMessage = new Server2GatewayMessage();
                gatewayMessage.setMessageId(message.getMessageId());
                gatewayMessage.setMessage(message);
                gatewayMessage.setUserIds(new Long[]{0L});

                logger.debug(message.toString());
                server.getChannel().writeAndFlush(gatewayMessage);
            }


        }
    }

}
