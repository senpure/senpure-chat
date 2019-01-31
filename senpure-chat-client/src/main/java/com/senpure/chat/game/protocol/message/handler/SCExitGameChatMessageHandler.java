package com.senpure.chat.game.protocol.message.handler;

import com.senpure.chat.game.protocol.message.SCExitGameChatMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time      2018-12-28 10:54:29
 */
@Component
public class SCExitGameChatMessageHandler extends AbstractMessageHandler<SCExitGameChatMessage> {

    @Override
    public void execute(Channel channel, SCExitGameChatMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
               // 2018-12-28 10:54:29 4000202
        return SCExitGameChatMessage.MESSAGE_ID;
    }

    @Override
    public SCExitGameChatMessage getEmptyMessage() {
        return new SCExitGameChatMessage();
    }

}