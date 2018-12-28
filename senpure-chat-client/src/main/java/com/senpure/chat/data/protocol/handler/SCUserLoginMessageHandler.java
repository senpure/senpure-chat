package com.senpure.chat.data.protocol.handler;

import com.senpure.chat.client.ui.view.ClientController;
import com.senpure.chat.data.protocol.message.SCUserLoginMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time      2018-12-26 16:02:24
 */
@Component
public class SCUserLoginMessageHandler extends AbstractMessageHandler<SCUserLoginMessage> {

    @Autowired
  private   ClientController clientController;
    @Override
    public void execute(Channel channel, SCUserLoginMessage message) {



        clientController.loginSuccess(message);

    }

    @Override
    public int handlerId() {
               // 2018-12-26 16:02:24 1000101
        return SCUserLoginMessage.MESSAGE_ID;
    }

    @Override
    public SCUserLoginMessage getEmptyMessage() {
        return new SCUserLoginMessage();
    }

}