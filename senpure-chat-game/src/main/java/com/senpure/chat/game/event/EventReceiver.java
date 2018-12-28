package com.senpure.chat.game.event;


import com.senpure.io.event.EventHelper;
import com.senpure.io.event.SinkLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * EventSender
 *
 * @author senpure
 * @time 2018-12-14 17:18:19
 */

@EnableBinding({SinkLogin.class})
public class EventReceiver {


    private Logger logger = LoggerFactory.getLogger(EventReceiver.class);


    @StreamListener(SinkLogin.LOGIN_INPUT)
    public void receive(byte[] bytes) {
        EventHelper.readAndExecute(bytes);


    }


}
