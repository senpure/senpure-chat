package com.senpure.chat.free.protocol.message.handler;

import com.senpure.chat.free.logic.GameRoom;
import com.senpure.chat.free.logic.Player;
import com.senpure.chat.free.logic.RoomManager;
import com.senpure.chat.free.protocol.message.CSCreateFreeChatMessage;
import com.senpure.io.handler.AbstractRealityMessageHandler;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:56:01
 */
@Component
public class CSCreateFreeChatMessageHandler extends AbstractRealityMessageHandler<CSCreateFreeChatMessage> {
    @Autowired
    private RoomManager roomManager;
    @Override
    public void execute(Channel channel, long token, long userId, CSCreateFreeChatMessage message) {
        GameRoom room = roomManager.createRoom();

        Player player = new Player();
        player.setId(userId);
        room.playerEntryRoom(player);

    }

    @Override
    public int handlerId() {
                //2018-12-28 10:56:01 3000200
        return CSCreateFreeChatMessage.MESSAGE_ID;
    }

    @Override
    public CSCreateFreeChatMessage getEmptyMessage() {
        return new CSCreateFreeChatMessage();
    }
}