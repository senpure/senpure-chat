package com.senpure.chat.data.protocol.handler;

import com.senpure.chat.data.model.User;
import com.senpure.chat.data.protocol.message.CSUserLoginMessage;
import com.senpure.chat.data.protocol.message.SCUserLoginMessage;
import com.senpure.chat.data.service.UserService;
import com.senpure.io.handler.AbstractRealityMessageHandler;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-25 11:29:12
 */
@Component
public class CSUserLoginMessageHandler extends AbstractRealityMessageHandler<CSUserLoginMessage> {

    @Autowired
    private UserService userService;

    @Override
    public void execute(Channel channel, long token, long userId, CSUserLoginMessage message) {

        User user = userService.login(message.getId(),token, message.getNick());
        if (user != null) {
            SCUserLoginMessage loginMessage = new SCUserLoginMessage();
            loginMessage.setUser(userService.convert(user));
            gatewayManager.sendLoginSuccessMessage2Gateway(token,user.getId(),loginMessage);
        }

    }

    @Override
    public int handlerId() {
        //2018-12-25 11:29:12 1000100
        return CSUserLoginMessage.MESSAGE_ID;
    }

    @Override
    public CSUserLoginMessage getEmptyMessage() {
        return new CSUserLoginMessage();
    }
}