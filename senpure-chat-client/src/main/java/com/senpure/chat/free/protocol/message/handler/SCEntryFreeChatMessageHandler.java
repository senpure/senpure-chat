package com.senpure.chat.free.protocol.handler;

import com.senpure.chat.free.protocol.message.SCEntryFreeChatMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time      2018-12-28 10:54:29
 */
@Component
public class SCEntryFreeChatMessageHandler extends AbstractMessageHandler<SCEntryFreeChatMessage> {

    @Override
    public void execute(Channel channel, SCEntryFreeChatMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
               // 2018-12-28 10:54:29 3000201
        return SCEntryFreeChatMessage.MESSAGE_ID;
    }

    @Override
    public SCEntryFreeChatMessage getEmptyMessage() {
        return new SCEntryFreeChatMessage();
    }

}