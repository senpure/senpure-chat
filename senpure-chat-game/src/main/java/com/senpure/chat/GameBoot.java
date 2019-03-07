package com.senpure.chat;

import com.senpure.io.support.annotation.EnableProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * GameBoot
 *
 * @author senpure
 * @time 2018-12-17 13:16:18
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableProducer
@EnableFeignClients
public class GameBoot {
    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(GameBoot.class, args);


    }
}
