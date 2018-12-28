package com.senpure.chat.game.protocol.handler;

import com.senpure.chat.game.protocol.message.CSCreateGameChatMessage;
import com.senpure.io.handler.AbstractRealityMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:57:04
 */
@Component
public class CSCreateGameChatMessageHandler extends AbstractRealityMessageHandler<CSCreateGameChatMessage> {

    @Override
    public void execute(Channel channel, long token, long userId, CSCreateGameChatMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
                //2018-12-28 10:57:04 4000200
        return CSCreateGameChatMessage.MESSAGE_ID;
    }

    @Override
    public CSCreateGameChatMessage getEmptyMessage() {
        return new CSCreateGameChatMessage();
    }
}