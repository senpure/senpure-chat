package com.senpure.chat.free.service;


import com.senpure.chat.free.logic.Player;
import com.senpure.chat.protocol.bean.User;

/**
 * RoomService
 *
 * @author senpure
 * @time 2018-12-28 11:39:59
 */
public class RoomService {


    public static User convert(Player player) {
        User user = new User();
        user.setUserId(player.getId());

        user.setNick(player.getNick());
        user.setDiamond(player.getDiamond());
        return user;

    }
}
