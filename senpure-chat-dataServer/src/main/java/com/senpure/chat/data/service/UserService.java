package com.senpure.chat.data.service;


import com.senpure.chat.data.entity.UserEntity;
import com.senpure.chat.protocol.event.EventSender;
import com.senpure.chat.protocol.bean.User;
import com.senpure.chat.protocol.event.UserDiamondChangeEvent;
import com.senpure.chat.protocol.event.UserLoginEvent;
import com.senpure.chat.protocol.event.UserLogoutEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * UserService
 *
 * @author senpure
 * @time 2018-12-14 17:05:19
 */
@Service
public class UserService {

    private AtomicLong idgeatrator = new AtomicLong(100000);
    @Autowired
    private EventSender eventSender;

    private Map<String, UserEntity> userStrMap = new ConcurrentHashMap<>();
   private Map<Long, UserEntity> userIdMap = new ConcurrentHashMap<>();
    public UserEntity login(String id, String nick) {
        UserEntity user = userStrMap.get(id);
        if (user == null) {
            user = new UserEntity();
            user.setUserId(idgeatrator.getAndIncrement());
            user.setStrId(id);
            if (nick == null || nick.length() == 0) {
                user.setNick("游客" + user.getUserId());
            } else {
                user.setNick(nick);
            }

            addDiamond("init", user, 500);
        }

        user.setStrId(id);
        userStrMap.put(id, user);

        userIdMap.put(user.getUserId(), user);
        UserLoginEvent event = new UserLoginEvent();
        event.setTime(System.currentTimeMillis());
        event.setUser(convert(user));
        eventSender.send(event);
        return user;
    }

    public void logout(long userId) {
        UserEntity user = userIdMap.remove(userId);
        if (user != null) {
            userStrMap.remove(user.getStrId());
            UserLogoutEvent event = new UserLogoutEvent();
            event.setLoginTime(user.getLoginTime());
            event.setLogoutTime(System.currentTimeMillis());
            event.setUser(new User());
            eventSender.send(event);
        }
    }

    public User convert(UserEntity source) {
        return convert(source, new User());
    }

    public User convert(UserEntity source, User target) {
        target.setUserId(source.getUserId());
        target.setNick(source.getNick());
        target.setDiamond(source.getDiamond());
        return target;
    }

    public void addDiamond(String type, long userId, long diamond) {
        UserEntity user = userIdMap.get(userId);
        if (user != null) {
            addDiamond(type, user, diamond);
        }
    }
    private  void addDiamond(String type, UserEntity user, long diamond) {
        long before = user.getDiamond();
        user.setDiamond(before + diamond);
        UserDiamondChangeEvent event = new UserDiamondChangeEvent();
        event.setChangeType(type);
        event.setBefore(before);
        event.setAfter(user.getDiamond());
        event.setNum(diamond);
        event.setTime(System.currentTimeMillis());
        User u = convert(user);
        event.setUser(u);
        eventSender.send(event);

    }

}
