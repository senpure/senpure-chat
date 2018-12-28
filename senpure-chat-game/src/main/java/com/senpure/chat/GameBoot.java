package com.senpure.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * GameBoot
 *
 * @author senpure
 * @time 2018-12-17 13:16:18
 */
@SpringBootApplication
public class GameBoot {
    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(GameBoot.class, args);

        Thread.sleep(500000000);
    }
}
