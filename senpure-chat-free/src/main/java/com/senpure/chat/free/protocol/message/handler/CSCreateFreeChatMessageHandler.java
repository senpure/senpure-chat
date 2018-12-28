package com.senpure.chat.free.protocol.handler;

import com.senpure.chat.free.protocol.message.CSCreateFreeChatMessage;
import com.senpure.io.handler.AbstractRealityMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:56:01
 */
@Component
public class CSCreateFreeChatMessageHandler extends AbstractRealityMessageHandler<CSCreateFreeChatMessage> {

    @Override
    public void execute(Channel channel, long token, long userId, CSCreateFreeChatMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
                //2018-12-28 10:56:01 3000200
        return CSCreateFreeChatMessage.MESSAGE_ID;
    }

    @Override
    public CSCreateFreeChatMessage getEmptyMessage() {
        return new CSCreateFreeChatMessage();
    }
}