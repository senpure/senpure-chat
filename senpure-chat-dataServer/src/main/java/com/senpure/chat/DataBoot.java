package com.senpure.chat;

import com.senpure.io.support.annotation.EnableProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * DataBoot
 *
 * @author senpure
 * @time 2018-12-14 18:07:46
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableProducer(breakUser = false)
public class DataBoot {

    public static void main(String[] args) {
        SpringApplication.run(DataBoot.class, args);
    }
}
