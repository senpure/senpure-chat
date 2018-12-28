package com.senpure.chat.protocol.message.handler;

import com.senpure.chat.protocol.message.CSJoinRoomMessage;
import com.senpure.io.handler.AbstractRealityMessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:56:01
 */
@Component
public class CSJoinRoomMessageHandler extends AbstractRealityMessageHandler<CSJoinRoomMessage> {

    @Override
    public void execute(Channel channel, long token, long userId, CSJoinRoomMessage message) {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
                //2018-12-28 10:56:01 2000102
        return CSJoinRoomMessage.MESSAGE_ID;
    }

    @Override
    public CSJoinRoomMessage getEmptyMessage() {
        return new CSJoinRoomMessage();
    }
}