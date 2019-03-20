package com.senpure.chat.game.logic;

import com.senpure.base.util.RandomUtil;
import com.senpure.io.server.GatewayManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

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

    public Set<Integer> roomIds = new LinkedHashSet<>();

    public BlockingDeque<Integer> using;

    public RoomManager() {
        easyRoom();
    }

    public GameRoom createRoom() {
        int roomId = nextRoomId();
        GameRoom room = new GameRoom(roomId, gatewayManager, roomManager);
        roomMap.put(roomId, room);
        return room;
    }

    public void closeRoom(GameRoom room) {
        roomMap.remove(room.getRoomId());
        back(room.getRoomId());

    }

    private int nextRoomId() {
        int roomId = nextRoomId();
        if (roomId == 0) {
            do {
                roomId = RandomUtil.random(100000, 999999);
            }
            while (!canUse(roomId) && roomMap.get(roomId) != null);
        }
        return roomId;
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


    private boolean canUse(Integer value) {

        return value <= 500000;
    }

    private Integer getEasyRoomId() {
        Integer roomId = using.poll();
        return roomId == null ? 0 : roomId;
    }

    private void back(Integer roomId) {
        if (roomIds.contains(roomId)) {
            // using.offerFirst(roomId);
            using.offerLast(roomId);
            //using.offer(roomId);
        }
    }

    private String leftIds() {
        return using.toString();
    }


    private void easyRoom() {
        //22222
        for (int i = 1; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                sb.append(i);
            }
            Integer id = Integer.valueOf(sb.toString());
            if (canUse(id)) {
                roomIds.add(id);
            }
        }
        List<Integer> temps = new ArrayList<>();
        temps.add(123456);
        temps.add(654321);
        for (Integer id : temps) {
            if (canUse(id)) {
                roomIds.add(id);
            }
        }

        //111222
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (i == j || i == j) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(i).append(i).append(i).append(j).append(j).append(j);
                Integer id = Integer.valueOf(sb.toString());
                if (canUse(id)) {
                    roomIds.add(id);
                }
            }

        }
        //112233
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                for (int k = 1; k < 10; k++) {
                    if (i == j || j == k || i == j) {
                        continue;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(i).append(i).append(j).append(j);
                    sb.append(k).append(k);
                    Integer id = Integer.valueOf(sb.toString());
                    if (canUse(id)) {
                        roomIds.add(id);
                    }
                }
            }

        }
        //122222

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {

                if (i == j || i == j) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(i).append(j).append(j).append(j);
                sb.append(j).append(j);
                Integer id = Integer.valueOf(sb.toString());
                if (canUse(id)) {
                    roomIds.add(id);
                }

            }

        }
        using = new LinkedBlockingDeque<>(roomIds.size());
        List<Integer> ids = new ArrayList<>(roomIds);
        Collections.shuffle(ids);
        ids.forEach(id ->
                using.offer(id)
        );

    }

}
