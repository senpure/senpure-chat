package com.senpure.chat.game.service;

import com.senpure.base.result.ItemResult;
import com.senpure.base.result.Result;
import com.senpure.chat.data.model.User;
import com.senpure.chat.data.result.UserRecordResult;
import com.senpure.chat.game.logic.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PlayerServer
 *
 * @author senpure
 * @time 2019-02-13 13:57:28
 */
@Service
public class PlayerServer {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PlayerServerRemote remote;

    public Player findPlayer(long userId) {

        UserRecordResult result = remote.readUser(userId);
        if (result.getCode() == Result.SUCCESS) {

            logger.info("{}",result.getUser());
            User user = result.getUser();

            Player player = new Player();
            player.setId(user.getId());
            player.setDiamond(user.getDiamond());
            player.setNick(user.getNick());
            return player;
        }
        return null;
    }
}
