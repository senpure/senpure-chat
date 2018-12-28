package com.senpure.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * DataBoot
 *
 * @author senpure
 * @time 2018-12-14 18:07:46
 */
@SpringBootApplication
@EnableEurekaClient
public class DataBoot {

    public static void main(String[] args) {
        SpringApplication.run(DataBoot.class, args);
    }
}
