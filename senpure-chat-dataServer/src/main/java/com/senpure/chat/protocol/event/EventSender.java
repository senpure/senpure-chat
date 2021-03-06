package com.senpure.chat.protocol.event;

import com.senpure.io.event.EventHelper;
import com.senpure.io.event.SourceLogin;
import com.senpure.io.event.SourceLogout;
import com.senpure.io.message.CSRelationUserGatewayMessage;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * EventSender
 *
 * @author senpure
 * @time 2018-12-14 17:18:19
 */
@EnableBinding({SourceLogin.class, SourceLogout.class, SourceDiamondChange.class})
public class EventSender {

    @Autowired
    private SourceLogin login;

    @Autowired
    private SourceLogout logout;
    @Autowired
    private SourceDiamondChange diamondChange;

    private Logger logger = LoggerFactory.getLogger(EventSender.class);

    public void send(UserLoginEvent event) {
        logger.debug("send {}", event.toString());
        login.output().send(MessageBuilder.withPayload(EventHelper.write(event)).build());
    }

    public void send(UserLogoutEvent event) {
        logger.debug("send {}", event.toString());
        logout.output().send(MessageBuilder.withPayload(EventHelper.write(event)).build());
    }

    public void send(UserDiamondChangeEvent event) {
        logger.debug("send 22{}", event.toString());
        diamondChange.output().send(MessageBuilder.withPayload(EventHelper.write(event)).build());
    }

    public static void main(String[] args) {
        CSRelationUserGatewayMessage message = new CSRelationUserGatewayMessage();
        message.setToken(RandomUtils.nextLong(1000000000000L, 9999999999999L));
        message.setUserId(500);
        message.setRelationToken(RandomUtils.nextLong(1000000000000L, 9999999999999L));

        System.out.println(message.getSerializedSize());
    }
}
