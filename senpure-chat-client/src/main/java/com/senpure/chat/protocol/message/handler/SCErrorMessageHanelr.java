package com.senpure.chat.protocol.message.handler;

import com.senpure.chat.client.ui.view.ClientController;
import com.senpure.chat.protocol.message.SCErrorMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SCErrorMessageHanelr
 *
 * @author senpure
 * @time 2019-03-20 18:10:38
 */
@Component
public class SCErrorMessageHanelr extends AbstractMessageHandler<SCErrorMessage> {
    @Autowired
    private ClientController clientController;

    @Override
    public void execute(Channel channel, SCErrorMessage message) throws Exception {
        clientController.message("ERROR:" + message.getMessage());
    }

    @Override
    public int handlerId() {
        return SCErrorMessage.MESSAGE_ID;
    }
}
