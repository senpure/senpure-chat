package com.senpure.chat.game.logic;

import com.senpure.chat.game.protocol.message.SCEntryGameChatMessage;
import com.senpure.chat.game.protocol.message.SCExitGameChatMessage;
import com.senpure.chat.game.protocol.message.SCGameChatMessage;
import com.senpure.chat.game.service.RoomService;
import com.senpure.io.server.GatewayManager;

import java.util.ArrayList;
import java.util.List;

/**
 * RoomMessage
 *
 * @author senpure
 * @time 2018-12-28 13:39:04
 */
public class RoomMessage {

    private GameRoom room;

    private GatewayManager gatewayManager;

    public RoomMessage(GameRoom room, GatewayManager gatewayManager) {
        this.room = room;
        this.gatewayManager = gatewayManager;
    }

    public void sendPlayerEntryRoomMessage(Player player) {

        SCEntryGameChatMessage message = new SCEntryGameChatMessage();
        message.setRoomId(room.getRoomId());
        message.setUser(RoomService.convert(player));

        List<Long> userIds = new ArrayList<>(room.getPlayers().keySet());

        gatewayManager.sendMessage2Gateway(userIds, message);

    }
    public void sendPlayerExitRoomMessage(Player player) {
        SCExitGameChatMessage message = new SCExitGameChatMessage();
        message.setRoomId(room.getRoomId());
        message.setUser(RoomService.convert(player));
        List<Long> userIds = new ArrayList<>(room.getPlayers().keySet());
        gatewayManager.sendMessage2Gateway(userIds, message);

    }

    public void sendPlayerChatMessage(Player player, String type, String content) {
        SCGameChatMessage message = new SCGameChatMessage();
        message.setUserId(player.getId());
        message.setType(type);
        message.setTitle(player.getNick() + "[" + player.getDiamond() + "]");
        message.setContent(content);
        List<Long> userIds = new ArrayList<>(room.getPlayers().keySet());
        gatewayManager.sendMessage2Gateway(userIds, message);

    }
}
