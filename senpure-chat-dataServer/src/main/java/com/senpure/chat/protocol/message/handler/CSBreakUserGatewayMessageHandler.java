package com.senpure.chat.protocol.message.handler;

import com.senpure.chat.data.service.UserService;
import com.senpure.io.handler.AbstractInnerMessageHandler;
import com.senpure.io.message.CSBreakUserGatewayMessage;
import com.senpure.io.server.GatewayManager;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CSBreakUserGatewayMessageHandler
 *
 * @author senpure
 * @time 2018-12-14 11:15:03
 */
@Component
public class CSBreakUserGatewayMessageHandler extends AbstractInnerMessageHandler<CSBreakUserGatewayMessage> {
    @Autowired
    private GatewayManager gatewayManager;
    @Autowired
    private UserService userService;

    public CSBreakUserGatewayMessageHandler() {
    }

    public void execute(Channel channel, long token, long userId, CSBreakUserGatewayMessage message) {
        this.gatewayManager.breakToken(message.getToken(), message.getRelationToken());
        if (this.gatewayManager.breakUser(message.getUserId(), message.getRelationToken())) {
            userService.logout(message.getUserId(), token);
        }

    }

    public int handlerId() {
        return 1201;
    }

    public CSBreakUserGatewayMessage getEmptyMessage() {
        return new CSBreakUserGatewayMessage();
    }
}