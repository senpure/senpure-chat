package com.senpure.chat.game.protocol.message.handler;

import com.senpure.chat.game.protocol.message.CSGameChatMessage;
import com.senpure.io.handler.AbstractRealityMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:57:04
 */
@Component
public class CSGameChatMessageHandler extends AbstractRealityMessageHandler<CSGameChatMessage> {

    @Override
    public void execute(Channel channel, long token, long userId, CSGameChatMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
                //2018-12-28 10:57:04 4000100
        return CSGameChatMessage.MESSAGE_ID;
    }

    @Override
    public CSGameChatMessage getEmptyMessage() {
        return new CSGameChatMessage();
    }
}