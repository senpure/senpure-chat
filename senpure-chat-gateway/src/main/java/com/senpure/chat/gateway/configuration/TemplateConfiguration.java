package com.senpure.chat.gateway.configuration;

import com.senpure.io.server.RealityServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TemplateConfiguration
 *
 * @author senpure
 * @time 2019-03-13 16:00:06
 */
@Configuration
public class TemplateConfiguration {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public RealityServer realityServer()
    {
        logger.info("777777777777777777777777777777");
        return null;
    }

}
