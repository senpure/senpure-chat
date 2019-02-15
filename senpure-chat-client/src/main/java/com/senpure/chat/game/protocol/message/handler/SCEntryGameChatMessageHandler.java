package com.senpure.chat.game.protocol.message.handler;

import com.senpure.chat.client.ui.view.ClientController;
import com.senpure.chat.game.protocol.message.SCEntryGameChatMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:54:29
 */
@Component
public class SCEntryGameChatMessageHandler extends AbstractMessageHandler<SCEntryGameChatMessage> {

    @Autowired
    private ClientController clientController;

    @Override
    public void execute(Channel channel, SCEntryGameChatMessage message) {

        clientController.loginRoom(message);
    }

    @Override
    public int handlerId() {
        // 2018-12-28 10:54:29 4000201
        return SCEntryGameChatMessage.MESSAGE_ID;
    }

    @Override
    public SCEntryGameChatMessage getEmptyMessage() {
        return new SCEntryGameChatMessage();
    }

}