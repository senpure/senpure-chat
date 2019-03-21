package com.senpure.chat.game.protocol.message.handler;

import com.senpure.chat.game.logic.GameRoom;
import com.senpure.chat.game.logic.Player;
import com.senpure.chat.game.logic.RoomManager;
import com.senpure.chat.game.protocol.message.CSCreateGameChatMessage;
import com.senpure.chat.game.service.PlayerServer;
import com.senpure.chat.protocol.message.SCErrorMessage;
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
    @Autowired
    private PlayerServer playerServer;

    @Override
    public void execute(Channel channel, long token, long userId, CSCreateGameChatMessage message) {
        if (userId == 0) {
            SCErrorMessage errorMessage = new SCErrorMessage();
            errorMessage.setMessage("没有登录--------");
            gatewayManager.sendMessage2GatewayByToken(token, errorMessage);
            return;
        }
        GameRoom room = roomManager.getPlayerRoom(userId);
        if (room != null) {
            Player player = playerServer.findPlayer(userId);
            logger.info("{}{}进入之前的房间{}", player.getId(), player.getNick(), room.getRoomId());
            room.playerEntryRoom(player);
            return;
        }
        room = roomManager.createRoom();
        Player player = playerServer.findPlayer(userId);
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