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

    private ConcurrentHashMap<Integer, GameRoom> roomMap = new ConcurrentHashMap<>();

    public GameRoom createRoom() {
        int roomId = 0;
        do {
            roomId = RandomUtil.random(100000, 999999);
        }
        while (roomMap.get(roomId) != null);
        GameRoom room = new GameRoom(roomId, gatewayManager);
        roomMap.put(roomId, room);
        return room;
    }

    public GameRoom getRoom(int roomId) {
        return roomMap.get(roomId);
    }

}
