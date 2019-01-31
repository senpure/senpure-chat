package com.senpure.chat.protocol.event.handler;

import com.senpure.chat.protocol.event.UserLoginEvent;
import com.senpure.io.handler.AbstractEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:56:01
 */
@Component
public class UserLoginEventHandler extends AbstractEventHandler<UserLoginEvent> {

    @Override
    public void execute(UserLoginEvent event) {
        logger.info("{}", event);

    }

    @Override
    public int handlerId() {
        //2018-12-28 10:56:01 100100
        return UserLoginEvent.EVENT_ID;
    }

    @Override
    public UserLoginEvent getEmptyEvent() {
        return new UserLoginEvent();
    }
}