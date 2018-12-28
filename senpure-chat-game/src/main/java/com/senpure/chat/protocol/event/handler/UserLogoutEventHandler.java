package com.senpure.chat.protocol.event.handler;

import com.senpure.chat.protocol.event.UserLogoutEvent;
import com.senpure.io.handler.AbstractEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:57:25
 */
@Component
public class UserLogoutEventHandler extends AbstractEventHandler<UserLogoutEvent> {

    @Override
    public void execute(UserLogoutEvent event) {
        //TODO 请在这里写下你处理UserLogoutEvent的代码，并删除该条注释

    }

    @Override
    public int handlerId() {
                //2018-12-28 10:57:25 100101
        return UserLogoutEvent.EVENT_ID;
    }

    @Override
    public UserLogoutEvent getEmptyEvent() {
        return new UserLogoutEvent();
    }
}