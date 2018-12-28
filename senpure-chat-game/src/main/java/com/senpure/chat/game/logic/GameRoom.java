package com.senpure.chat.game.logic;

import com.senpure.io.server.GatewayManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GameRoom
 *
 * @author senpure
 * @time 2018-12-28 11:42:58
 */
public class GameRoom {


    private int roomId;

    private RoomMessage message;
    private Map<Long, Player> players = new ConcurrentHashMap<>();

    public GameRoom(int roomId, GatewayManager gatewayManager) {
        this.roomId = roomId;
        message = new RoomMessage(this,gatewayManager);
    }




    public void  playerEntryRoom(Player player) {
        players.put(player.getId(), player);
        message.sendPlayerEntryRoomMessage(player);
    }


    public void  playerChat(Player player,String type,String content) {
        message.sendPlayerChatMessage(player,type,content);
    }


    public void  playerExitRoom(Player player) {
        players.remove(player.getId());
        message.sendPlayerEntryRoomMessage(player);
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
