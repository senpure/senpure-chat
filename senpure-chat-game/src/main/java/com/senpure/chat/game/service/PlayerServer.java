package com.senpure.chat.game.service;

import com.senpure.chat.game.logic.Player;
import org.springframework.stereotype.Service;

/**
 * PlayerServer
 *
 * @author senpure
 * @time 2019-02-13 13:57:28
 */
@Service
public class PlayerServer {

    public Player findPlayer(long userId)
    {

        Player player = new Player();
        player.setId(userId);
        player.setDiamond(500);
        player.setNick("jia-nick"+userId);
        return player;
    }
}
