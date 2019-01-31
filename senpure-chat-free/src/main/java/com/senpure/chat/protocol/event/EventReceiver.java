package com.senpure.chat.protocol.event;

import com.senpure.io.event.EventHelper;
import com.senpure.io.event.SinkLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * EventReceiver
 *
 * @author senpure
 * @time 2018-11-09 15:11:24
 */
@EnableBinding({SinkLogin.class, SinkLogin.class})
public class EventReceiver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @StreamListener(SinkLogin.LOGIN_INPUT)
    public void receive(byte[] bytes) {
        EventHelper.readAndExecute(bytes);

    }
}
