package com.senpure.chat.protocol.message.handler;

import com.senpure.chat.client.ui.view.ClientController;
import com.senpure.io.message.SCInnerErrorMessage;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SSInner
 *
 * @author senpure
 * @time 2019-02-21 17:40:05
 */
@Component
public class SCInnerErrorMessageHandler extends com.senpure.io.handler.SCInnerErrorMessageHandler {
    @Autowired
    private ClientController clientController;

    @Override
    public void execute(Channel channel, SCInnerErrorMessage message) throws Exception {
        clientController.message(message.toString());
    }
}
