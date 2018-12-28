package com.senpure.chat.protocol.event.handler;

import com.senpure.chat.protocol.event.UserLoginEvent;
import com.senpure.io.handler.AbstractEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:57:25
 */
@Component
public class UserLoginEventHandler extends AbstractEventHandler<UserLoginEvent> {

    @Override
    public void execute(UserLoginEvent event) {
        //TODO 请在这里写下你处理UserLoginEvent的代码，并删除该条注释

    }

    @Override
    public int handlerId() {
                //2018-12-28 10:57:25 100100
        return UserLoginEvent.EVENT_ID;
    }

    @Override
    public UserLoginEvent getEmptyEvent() {
        return new UserLoginEvent();
    }
}