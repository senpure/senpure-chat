package com.senpure.chat.gateway;

import com.senpure.io.support.annotation.EnableGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * GatewayBoot
 *
 * @author senpure
 * @time 2018-12-14 10:49:21
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGateway
public class GatewayBoot {

    public static void main(String[] args) {


        SpringApplication.run(GatewayBoot.class, args);
    }
}
