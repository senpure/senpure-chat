package com.senpure.chat.gateway.configuration;


import com.senpure.io.IOServerProperties;
import com.senpure.io.gateway.GatewayAndClientServer;
import com.senpure.io.gateway.GatewayAndServerServer;
import com.senpure.io.gateway.GatewayMessageExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * GatewayConfiguration
 *
 * @author senpure
 * @time 2018-10-22 09:57:20
 */
@Configuration
@EnableConfigurationProperties(IOServerProperties.class)
public class GatewayConfiguration {


    @Autowired
    private IOServerProperties ioServerProperties;

    private GatewayAndClientServer gatewayAndClientServer;

    private GatewayAndServerServer gatewayAndServerServer;

    @Bean
    public GatewayMessageExecuter gatewayMessageExecuter() {
        GatewayMessageExecuter messageExecuter = new GatewayMessageExecuter();
        return messageExecuter;
    }

    @Bean
    public GatewayAndClientServer gatewayAndClientServer() throws CertificateException, SSLException {
        GatewayAndClientServer gatewayAndClientServer = new GatewayAndClientServer();
        gatewayAndClientServer.setProperties(ioServerProperties);
        GatewayMessageExecuter gatewayMessageExecuter = gatewayMessageExecuter();
        gatewayMessageExecuter.retainService();
        gatewayAndClientServer.setMessageExecuter(gatewayMessageExecuter);
        if (gatewayAndClientServer.start()) {
            this.gatewayAndClientServer = gatewayAndClientServer;
            return gatewayAndClientServer;
        } else {
            gatewayMessageExecuter.releaseAndTryShutdownService();

        }

        return null;
    }

    @Bean
    public GatewayAndServerServer gatewayAndServerServer() throws CertificateException, SSLException {
        GatewayAndServerServer gatewayAndServerServer = new GatewayAndServerServer();
        gatewayAndServerServer.setProperties(ioServerProperties);
        GatewayMessageExecuter gatewayMessageExecuter = gatewayMessageExecuter();
        gatewayMessageExecuter.setScLoginMessageId(100010);
        gatewayMessageExecuter.retainService();
        gatewayAndServerServer.setMessageExecuter(gatewayMessageExecuter);
        if (gatewayAndServerServer.start()) {
            this.gatewayAndServerServer = gatewayAndServerServer;
            return gatewayAndServerServer;
        } else {
            gatewayMessageExecuter.releaseAndTryShutdownService();
        }

        return null;
    }

    @PreDestroy
    public void destroy() {
        if (gatewayAndClientServer != null) {
            gatewayAndClientServer.destroy();
        }
        if (gatewayAndServerServer != null) {
            gatewayAndServerServer.destroy();
        }
    }
}
