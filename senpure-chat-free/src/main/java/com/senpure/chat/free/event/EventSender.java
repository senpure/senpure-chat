package com.senpure.chat.free.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.PostConstruct;

/**
 * EventSender
 *
 * @author senpure
 * @time 2018-12-14 17:18:19
 */

//@EnableBinding(Source.class)
public class EventSender {

    @Autowired
    private Source source;

    private Logger logger = LoggerFactory.getLogger(EventSender.class);

    public void send(Persion persion) {
        logger.debug("send {}", persion.getName());
        source.sendLogin().send(MessageBuilder.withPayload(persion).build());
    }

    @PostConstruct
    public void send() {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                int count = 0;
                while (true) {


                    Persion persion = new Persion();
                    persion.setName("persion" + count);
                    send(persion);
                    count++;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread.start();

    }
}
