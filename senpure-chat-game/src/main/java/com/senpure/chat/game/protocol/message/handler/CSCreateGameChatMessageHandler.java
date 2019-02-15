package com.senpure.chat.game.protocol.message.handler;

import com.senpure.chat.game.logic.GameRoom;
import com.senpure.chat.game.logic.Player;
import com.senpure.chat.game.logic.RoomManager;
import com.senpure.chat.game.protocol.message.CSCreateGameChatMessage;
import com.senpure.io.handler.AbstractRealityMessageHandler;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:57:04
 */
@Component
public class CSCreateGameChatMessageHandler extends AbstractRealityMessageHandler<CSCreateGameChatMessage> {

    @Autowired
    private RoomManager roomManager;

    @Override
    public void execute(Channel channel, long token, long userId, CSCreateGameChatMessage message) {

        GameRoom room = roomManager.createRoom();

        Player player = new Player();
        player.setId(userId);
        room.playerEntryRoom(player);

    }

    @Override
    public int handlerId() {
        //2018-12-28 10:57:04 4000200
        return CSCreateGameChatMessage.MESSAGE_ID;
    }

    @Override
    public CSCreateGameChatMessage getEmptyMessage() {
        return new CSCreateGameChatMessage();
    }
}