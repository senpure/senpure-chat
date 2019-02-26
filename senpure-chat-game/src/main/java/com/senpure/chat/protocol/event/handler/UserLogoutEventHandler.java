package com.senpure.chat.protocol.event.handler;

import com.senpure.chat.game.logic.GameRoom;
import com.senpure.chat.game.logic.RoomManager;
import com.senpure.chat.game.service.PlayerServer;
import com.senpure.chat.protocol.event.UserLogoutEvent;
import com.senpure.io.handler.AbstractEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:57:25
 */
@Component
public class UserLogoutEventHandler extends AbstractEventHandler<UserLogoutEvent> {

    @Autowired
    private RoomManager roomManager;
    @Autowired
    private PlayerServer playerServer;

    @Override
    public void execute(UserLogoutEvent event) {

        logger.debug("{}", event);
        GameRoom room = roomManager.getPlayerRoom(event.getUser().getUserId());
        if (room != null) {
            room.playerExitRoom(event.getUser().getUserId());
        }
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