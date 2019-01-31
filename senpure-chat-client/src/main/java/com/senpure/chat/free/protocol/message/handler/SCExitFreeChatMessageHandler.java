package com.senpure.chat.free.protocol.message.handler;

import com.senpure.chat.free.protocol.message.SCExitFreeChatMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time      2018-12-28 10:54:29
 */
@Component
public class SCExitFreeChatMessageHandler extends AbstractMessageHandler<SCExitFreeChatMessage> {

    @Override
    public void execute(Channel channel, SCExitFreeChatMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
               // 2018-12-28 10:54:29 3000202
        return SCExitFreeChatMessage.MESSAGE_ID;
    }

    @Override
    public SCExitFreeChatMessage getEmptyMessage() {
        return new SCExitFreeChatMessage();
    }

}