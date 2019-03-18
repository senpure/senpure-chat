package com.senpure.chat.game.logic;

import com.senpure.base.util.RandomUtil;
import com.senpure.io.server.GatewayManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * RoomManager
 *
 * @author senpure
 * @time 2019-02-12 10:10:54
 */
@Component
public class RoomManager {

    @Autowired
    private GatewayManager gatewayManager;
    @Autowired
    private RoomManager roomManager;

    private ConcurrentHashMap<Integer, GameRoom> roomMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, GameRoom> playerRoomMap = new ConcurrentHashMap<>();

    public GameRoom createRoom() {
        int roomId = 0;
        do {
            roomId = RandomUtil.random(100000, 999999);
        }
        while (roomMap.get(roomId) != null);

        GameRoom room = new GameRoom(roomId, gatewayManager, roomManager);
        roomMap.put(roomId, room);
        return room;
    }

    public GameRoom getRoom(int roomId) {
        return roomMap.get(roomId);
    }


    public GameRoom markPlayerRoom(Long playerId, GameRoom room) {
        return playerRoomMap.put(playerId, room);
    }

    public GameRoom getPlayerRoom(Long playerId) {
        return playerRoomMap.get(playerId);
    }



}
