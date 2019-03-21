package com.senpure.chat.game.protocol.message.handler;

import com.senpure.chat.game.logic.GameRoom;
import com.senpure.chat.game.logic.Player;
import com.senpure.chat.game.logic.RoomManager;
import com.senpure.chat.game.protocol.message.CSGameChatMessage;
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
public class CSGameChatMessageHandler extends AbstractRealityMessageHandler<CSGameChatMessage> {

    @Autowired
    private RoomManager roomManager;
    @Autowired
    private PlayerServer playerServer;

    @Override
    public void execute(Channel channel, long token, long userId, CSGameChatMessage message) {
        if (userId == 0) {
            SCErrorMessage errorMessage = new SCErrorMessage();
            errorMessage.setMessage("没有登录--------");
            gatewayManager.sendMessage2GatewayByToken(token, errorMessage);
            return;
        }
        GameRoom gameRoom = roomManager.getPlayerRoom(userId);
        if (gameRoom != null) {
            Player player = playerServer.findPlayer(userId);
            gameRoom.playerChat(player, "game", message.getContent());
        } else {
            SCErrorMessage errorMessage = new SCErrorMessage();
            errorMessage.setMessage("没有进入房间");
            gatewayManager.sendMessage2GatewayByToken(token, errorMessage);
            return;
        }
    }

    @Override
    public int handlerId() {
        //2018-12-28 10:57:04 4000100
        return CSGameChatMessage.MESSAGE_ID;
    }

    @Override
    public CSGameChatMessage getEmptyMessage() {
        return new CSGameChatMessage();
    }
}