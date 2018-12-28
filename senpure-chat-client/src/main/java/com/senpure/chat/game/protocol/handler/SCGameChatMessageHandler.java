package com.senpure.chat.game.protocol.handler;

import com.senpure.chat.game.protocol.message.SCGameChatMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time      2018-12-28 10:39:26
 */
@Component
public class SCGameChatMessageHandler extends AbstractMessageHandler<SCGameChatMessage> {

    @Override
    public void execute(Channel channel, SCGameChatMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
               // 2018-12-28 10:39:26 4000101
        return SCGameChatMessage.MESSAGE_ID;
    }

    @Override
    public SCGameChatMessage getEmptyMessage() {
        return new SCGameChatMessage();
    }

}