package com.senpure.chat.free.protocol.handler;

import com.senpure.chat.free.protocol.message.CSFreeChatMessage;
import com.senpure.io.handler.AbstractRealityMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:56:01
 */
@Component
public class CSFreeChatMessageHandler extends AbstractRealityMessageHandler<CSFreeChatMessage> {

    @Override
    public void execute(Channel channel, long token, long userId, CSFreeChatMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
                //2018-12-28 10:56:01 3000100
        return CSFreeChatMessage.MESSAGE_ID;
    }

    @Override
    public CSFreeChatMessage getEmptyMessage() {
        return new CSFreeChatMessage();
    }
}