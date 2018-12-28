package com.senpure.chat.client.configuration;

import com.senpure.base.util.NameThreadFactory;
import com.senpure.io.ClientServer;
import com.senpure.io.IOServerProperties;
import com.senpure.io.MessageHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * ClientConfiguration
 *
 * @author senpure
 * @time 2018-12-26 13:57:16
 */
@Configuration
@EnableConfigurationProperties(IOServerProperties.class)
public class ClientConfiguration {

    @Autowired
    private IOServerProperties properties;
    private ClientServer clientServer;

    private ScheduledExecutorService service;

    @Bean
    public ClientServer start() throws CertificateException, SSLException {

        service = Executors.newSingleThreadScheduledExecutor(new NameThreadFactory("senpure-chat-client"));
        MessageHandlerUtil.setService(service);
        ClientServer clientServer = new ClientServer();
        String[] temp = properties.getGatewayAddress().split(",");
        String[] oneAddressTemp = temp[0].split(":");
        String host = oneAddressTemp[0];
        int port = Integer.parseInt(oneAddressTemp[1]);
        clientServer.setProperties(properties);
        clientServer.setServerName("chat客户端模拟器");
        clientServer.start(host, port);
        this.clientServer = clientServer;
        return clientServer;
    }

    @PreDestroy
    public void destroy() {
        if (this.clientServer != null) {
            clientServer.destroy();
            service.shutdown();
        }
    }

}
