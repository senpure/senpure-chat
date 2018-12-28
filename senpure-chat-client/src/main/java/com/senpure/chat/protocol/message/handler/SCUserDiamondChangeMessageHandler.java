package com.senpure.chat.protocol.message.handler;

import com.senpure.chat.protocol.message.SCUserDiamondChangeMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time      2018-12-28 10:54:29
 */
@Component
public class SCUserDiamondChangeMessageHandler extends AbstractMessageHandler<SCUserDiamondChangeMessage> {

    @Override
    public void execute(Channel channel, SCUserDiamondChangeMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
               // 2018-12-28 10:54:29 2000104
        return SCUserDiamondChangeMessage.MESSAGE_ID;
    }

    @Override
    public SCUserDiamondChangeMessage getEmptyMessage() {
        return new SCUserDiamondChangeMessage();
    }

}