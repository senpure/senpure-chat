package com.senpure.chat.protocol.message.handler;

import com.senpure.chat.game.logic.GameRoom;
import com.senpure.chat.game.logic.Player;
import com.senpure.chat.game.logic.RoomManager;
import com.senpure.chat.game.service.PlayerServer;
import com.senpure.chat.protocol.message.CSJoinRoomMessage;
import com.senpure.chat.protocol.message.SCErrorMessage;
import com.senpure.io.handler.AbstractRealityAskMessageHandler;
import com.senpure.io.message.CSAskHandleMessage;
import com.senpure.io.message.SCAskHandleMessage;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:57:04
 */
@Component
public class CSJoinRoomMessageHandler extends AbstractRealityAskMessageHandler<CSJoinRoomMessage> {

    @Autowired
    private RoomManager roomManager;
    @Autowired
    private PlayerServer playerServer;

    @Override
    public void execute(Channel channel, long token, long userId, CSJoinRoomMessage message) {
        if (userId == 0) {
            SCErrorMessage errorMessage = new SCErrorMessage();
            errorMessage.setMessage("没有登录--------");
            gatewayManager.sendMessage2GatewayByToken(token, errorMessage);
            return;
        }
        GameRoom gameRoom = roomManager.getPlayerRoom(userId);
        if (gameRoom != null) {

            Player player = playerServer.findPlayer(userId);
            if (player != null) {
                logger.info("{}{}进入之前的房间{}", player.getId(), player.getNick(), gameRoom.getRoomId());
                gameRoom.playerEntryRoom(player);
            }
            return;
        }
        gameRoom = roomManager.getRoom(Integer.valueOf(message.getRoomId()));
        if (gameRoom != null) {
            Player player = playerServer.findPlayer(userId);
            if (player != null) {
                gameRoom.playerEntryRoom(player);
            }
        }
    }

    @Override
    public int handlerId() {
        //2018-12-28 10:57:04 2000102
        return CSJoinRoomMessage.MESSAGE_ID;
    }

    @Override
    public CSJoinRoomMessage getEmptyMessage() {
        return new CSJoinRoomMessage();
    }


    @Override
    public SCAskHandleMessage ask(CSAskHandleMessage message) {
        logger.info("询问是否能够加入房间 {}", message.getValue());
        if (roomManager.getRoom(Integer.valueOf(message.getValue())) != null) {
            logger.info("房间 {} 存在", message.getValue());
            SCAskHandleMessage scAskHandleMessage = new SCAskHandleMessage();
            scAskHandleMessage.setValue(message.getValue());
            scAskHandleMessage.setToken(message.getToken());
            scAskHandleMessage.setHandle(true);
            scAskHandleMessage.setFromMessageId(message.getFromMessageId());
            return scAskHandleMessage;
        }
        logger.info("房间 {} 不存在", message.getValue());
        return null;
    }
}