package com.senpure.chat.data.protocol.handler;

import com.senpure.chat.data.protocol.message.SCUserLoginMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time      2018-12-28 10:54:29
 */
@Component
public class SCUserLoginMessageHandler extends AbstractMessageHandler<SCUserLoginMessage> {

    @Override
    public void execute(Channel channel, SCUserLoginMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
               // 2018-12-28 10:54:29 1000101
        return SCUserLoginMessage.MESSAGE_ID;
    }

    @Override
    public SCUserLoginMessage getEmptyMessage() {
        return new SCUserLoginMessage();
    }

}