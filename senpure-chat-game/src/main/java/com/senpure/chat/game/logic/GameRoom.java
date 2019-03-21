package com.senpure.chat.game.logic;

import com.senpure.io.server.GatewayManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GameRoom
 *
 * @author senpure
 * @time 2018-12-28 11:42:58
 */
public class GameRoom {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private int roomId;

    private RoomMessage message;
    private Map<Long, Player> players = new ConcurrentHashMap<>();

    private RoomManager roomManager;

    public GameRoom(int roomId, GatewayManager gatewayManager, RoomManager roomManager) {
        this.roomId = roomId;
        message = new RoomMessage(this, gatewayManager);
        this.roomManager = roomManager;
    }


    public void playerEntryRoom(Player player) {
        players.put(player.getId(), player);
        roomManager.markPlayerRoom(player.getId(), this);
        logger.debug("{} 进入房间  {}", player.getId(), roomId);
        logger.info("players.size {}", players.size());
        message.sendPlayerEntryRoomMessage(player);
    }


    public void playerChat(Player player, String type, String content) {
        message.sendPlayerChatMessage(player, type, content);
    }


    public void playerExitRoom(Long playerId) {
        Player player = players.remove(playerId);
        if (player != null) {
            roomManager.playerExitRoom(playerId);
            message.sendPlayerExitRoomMessage(player);
        }

    }


    public Map<Long, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Long, Player> players) {
        this.players = players;
    }

    public int getRoomId() {
        return roomId;
    }
}
