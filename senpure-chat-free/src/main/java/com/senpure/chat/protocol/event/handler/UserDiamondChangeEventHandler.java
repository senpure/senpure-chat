package com.senpure.chat.protocol.event.handler;

import com.senpure.chat.protocol.event.UserDiamondChangeEvent;
import com.senpure.io.handler.AbstractEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:56:01
 */
@Component
public class UserDiamondChangeEventHandler extends AbstractEventHandler<UserDiamondChangeEvent> {

    @Override
    public void execute(UserDiamondChangeEvent event) {
        //TODO 请在这里写下你处理UserDiamondChangeEvent的代码，并删除该条注释

    }

    @Override
    public int handlerId() {
                //2018-12-28 10:56:01 100102
        return UserDiamondChangeEvent.EVENT_ID;
    }

    @Override
    public UserDiamondChangeEvent getEmptyEvent() {
        return new UserDiamondChangeEvent();
    }
}