package com.senpure.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EurekaBoot
 *
 * @author senpure
 * @time 2018-12-28 15:34:42
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaBoot {
    public static void main(String[] args) {

        SpringApplication.run(EurekaBoot.class, args);
    }
}
