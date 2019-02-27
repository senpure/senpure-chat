package com.senpure.chat.game.event;


import com.senpure.io.event.EventHelper;
import com.senpure.io.event.SinkLogin;
import com.senpure.io.event.SinkLogout;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * EventSender
 *
 * @author senpure
 * @time 2018-12-14 17:18:19
 */

@EnableBinding({SinkLogin.class, SinkLogout.class})
public class EventReceiver {

    // private Logger logger = LoggerFactory.getLogger(EventReceiver.class);
    @StreamListener(SinkLogin.LOGIN_INPUT)
    public void receiveLogin(byte[] bytes) {
        EventHelper.readAndExecute(bytes);
    }

    @StreamListener(SinkLogout.LOGOUT_INPUT)
    public void receiveLogout(byte[] bytes) {
        EventHelper.readAndExecute(bytes);
    }


}
