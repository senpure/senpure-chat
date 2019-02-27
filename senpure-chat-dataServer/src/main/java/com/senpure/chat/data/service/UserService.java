package com.senpure.chat.data.service;


import com.senpure.chat.data.model.User;
import com.senpure.chat.protocol.event.EventSender;
import com.senpure.chat.protocol.event.UserDiamondChangeEvent;
import com.senpure.chat.protocol.event.UserLoginEvent;
import com.senpure.chat.protocol.event.UserLogoutEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private EventSender eventSender;

    private AtomicLong idGenerator = new AtomicLong(100000);



    private Map<String, User> userStrMap = new ConcurrentHashMap<>();
    private Map<Long, User> userIdMap = new ConcurrentHashMap<>();

    public User login(String id, String nick) {
        User user = userStrMap.get(id);
        if (user == null) {
            user = new User();
            user.setId(idGenerator.getAndIncrement());
            user.setStrId(id);
            user.setDiamond(0L);
            if (nick == null || nick.length() == 0||nick.startsWith("游客")) {
                user.setNick("游客" + user.getId());
            } else {
                user.setNick(nick);
            }

            addDiamond("INIT", user, 500);
        }

        user.setStrId(id);
        user.setLoginTime(System.currentTimeMillis());
        user.setLoginDate(new Date(user.getLoginTime()));
        userStrMap.put(id, user);

        userIdMap.put(user.getId(), user);
        UserLoginEvent event = new UserLoginEvent();
        event.setTime(System.currentTimeMillis());
        event.setUser(convert(user));
        eventSender.send(event);
        return user;
    }

    public void logout(long userId) {
        User user = userIdMap.remove(userId);
        if (user != null) {
           // userStrMap.remove(user.getStrId());
            UserLogoutEvent event = new UserLogoutEvent();
            event.setLoginTime(user.getLoginTime());
            event.setLogoutTime(System.currentTimeMillis());
            event.setUser(convert(user));
            eventSender.send(event);
        }
    }

    public com.senpure.chat.protocol.bean.User convert(User source) {
        return convert(source, new com.senpure.chat.protocol.bean.User());
    }

    public com.senpure.chat.protocol.bean.User convert(User source, com.senpure.chat.protocol.bean.User target) {
        target.setUserId(source.getId());
        target.setNick(source.getNick());
        target.setDiamond(source.getDiamond());
        return target;
    }

    public void addDiamond(String type, long userId, long diamond) {
        User user = userIdMap.get(userId);
        if (user != null) {
            addDiamond(type, user, diamond);
        }
    }

    private void addDiamond(String type, User user, long diamond) {
        long before = user.getDiamond();
        user.setDiamond(before + diamond);
        UserDiamondChangeEvent event = new UserDiamondChangeEvent();
        event.setChangeType(type);
        event.setBefore(before);
        event.setAfter(user.getDiamond());
        event.setNum(diamond);
        event.setTime(System.currentTimeMillis());
        com.senpure.chat.protocol.bean.User u = convert(user);
        event.setUser(u);
        eventSender.send(event);

    }

}
