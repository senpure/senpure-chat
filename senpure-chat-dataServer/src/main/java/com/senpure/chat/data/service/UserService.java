package com.senpure.chat.data.service;


import com.senpure.chat.data.model.User;
import com.senpure.chat.protocol.event.EventSender;
import com.senpure.chat.protocol.event.UserDiamondChangeEvent;
import com.senpure.chat.protocol.event.UserLoginEvent;
import com.senpure.chat.protocol.event.UserLogoutEvent;
import com.senpure.chat.protocol.message.SCErrorMessage;
import com.senpure.io.server.GatewayManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @Autowired
    private GatewayManager gatewayManager;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Map<String, User> userStrMap = new ConcurrentHashMap<>();
    private Map<Long, User> userIdMap = new ConcurrentHashMap<>();

    public User findUser(long id) {
        User user = userIdMap.get(id);
        if (user == null && id < 10000) {
            return login("12346", 0L, "",id);
        }
        return userIdMap.get(id);
    }

    public User checkUser(String id) {

        return userStrMap.get(id);
    }

    public User login(String id, Long gatewayToken, String nick,long oldId) {
        User user = userStrMap.get(id);
        if (user == null) {
            user = new User();
            user.setId(idGenerator.getAndIncrement());
            user.setStrId(id);
            user.setDiamond(0L);
            user.setGatewayToken(0L);
            if (nick == null || nick.length() == 0 || nick.startsWith("游客")) {
                user.setNick("游客" + user.getId());
            } else {
                user.setNick(nick);
            }

            addDiamond("INIT", user, 500);
        }

        if (user.getGatewayToken() != 0) {
            long token = user.getGatewayToken();
            //不同设备登陆同一个号，踢掉之前登陆的
            if (token != gatewayToken.longValue()) {
                SCErrorMessage errorMessage = new SCErrorMessage();
                errorMessage.setMessage("账号在其他地方登陆");
                gatewayManager.sendMessage2GatewayByToken(token, errorMessage);
                gatewayManager.sendKickOffMessage2GatewayByToken(token);
                logger.info("{}[{}]其他地方重复登陆", user.getNick(), user.getId());
            }
        }
        //if()

        user.setStrId(id);
        user.setGatewayToken(gatewayToken);
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

    public void logout(long userId, long token) {
        logger.info("{},退出------------", userId);
        User user = userIdMap.remove(userId);
        if (user != null) {
            if (user.getGatewayToken() == token) {
                logger.info("{}[{}],退出成功------------", user.getNick(), user.getId());
                // userStrMap.remove(user.getStrId());
                user.setGatewayToken(0L);
                UserLogoutEvent event = new UserLogoutEvent();
                event.setLoginTime(user.getLoginTime());
                event.setLogoutTime(System.currentTimeMillis());
                event.setUser(convert(user));
                eventSender.send(event);
            } else {
                logger.info("{},退出失败无效退出token；{}", userId, token);
            }

        } else {
            logger.info("{},退出失败无效退出请求", userId);
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

    public static void main(String[] args) {

        String str = "{\"code\":1,\"user\":{\"diamond\":500,\"id\":100002,\"loginDate\":1551863302799,\"loginTime\":1551863302799,\"nick\":\"游客100002\",\"strId\":\"ca63533523741845550988f2b9d5751d137ca\"}}";


//        UserRecordResult userRecordResult = JacksonUtil.parseObject(str, UserRecordResult.class);
//
//        System.out.println(userRecordResult.getCode()+""+userRecordResult.toString());
    }

}
