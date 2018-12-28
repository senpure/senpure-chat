package com.senpure.chat.protocol.message.handler;

import com.senpure.chat.protocol.message.SCChatMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time      2018-12-26 16:02:24
 */
@Component
public class SCChatMessageHandler extends AbstractMessageHandler<SCChatMessage> {

    @Override
    public void execute(Channel channel, SCChatMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
               // 2018-12-26 16:02:24 2000101
        return SCChatMessage.MESSAGE_ID;
    }

    @Override
    public SCChatMessage getEmptyMessage() {
        return new SCChatMessage();
    }

}