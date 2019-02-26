package com.senpure.chat.game.protocol.message.handler;

import com.senpure.chat.client.ui.view.ClientController;
import com.senpure.chat.game.protocol.message.SCGameChatMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:54:29
 */
@Component
public class SCGameChatMessageHandler extends AbstractMessageHandler<SCGameChatMessage> {

    @Autowired
    private ClientController clientController;

    @Override
    public void execute(Channel channel, SCGameChatMessage message) {

        clientController.message(message.getTitle() + ":" + message.getContent());

    }

    @Override
    public int handlerId() {
        // 2018-12-28 10:54:29 4000101
        return SCGameChatMessage.MESSAGE_ID;
    }

    @Override
    public SCGameChatMessage getEmptyMessage() {
        return new SCGameChatMessage();
    }

}