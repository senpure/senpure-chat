package com.senpure.chat.client.configuration;

import com.senpure.base.util.NameThreadFactory;
import com.senpure.io.ClientServer;
import com.senpure.io.IOServerProperties;
import com.senpure.io.MessageHandlerUtil;
import com.senpure.io.handler.SCHeartMessageHandler;
import com.senpure.io.handler.SCInnerErrorMessageHandler;
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


    // @Bean
    public SCInnerErrorMessageHandler innerErrorMessageHandler() {
        return new SCInnerErrorMessageHandler();
    }

    @Bean
    public SCHeartMessageHandler heartMessageHandler() {
        return new SCHeartMessageHandler();
    }

    @Bean
    public ClientServer start() throws CertificateException, SSLException {

        service = Executors.newSingleThreadScheduledExecutor(new NameThreadFactory("senpure-chat-client"));
        MessageHandlerUtil.setService(service);
        ClientServer clientServer = new ClientServer();
        clientServer.setProperties(properties);
        clientServer.setServerName("chat客户端模拟器");
       // clientServer.connect();
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
