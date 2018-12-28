package com.senpure.chat.free.protocol.handler;

import com.senpure.chat.free.protocol.message.SCFreeChatMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time      2018-12-28 10:39:35
 */
@Component
public class SCFreeChatMessageHandler extends AbstractMessageHandler<SCFreeChatMessage> {

    @Override
    public void execute(Channel channel, SCFreeChatMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
               // 2018-12-28 10:39:35 3000101
        return SCFreeChatMessage.MESSAGE_ID;
    }

    @Override
    public SCFreeChatMessage getEmptyMessage() {
        return new SCFreeChatMessage();
    }

}