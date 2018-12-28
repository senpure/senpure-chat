package com.senpure.chat.protocol.message.handler;

import com.senpure.chat.protocol.message.CSJoinRoomMessage;
import com.senpure.io.handler.AbstractRealityAskMessageHandler;
import com.senpure.io.message.CSAskHandleMessage;
import com.senpure.io.message.SCAskHandleMessage;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author senpure
 * @time 2018-12-28 10:57:04
 */
@Component
public class CSJoinRoomMessageHandler extends AbstractRealityAskMessageHandler<CSJoinRoomMessage> {

    @Override
    public void execute(Channel channel, long token, long userId, CSJoinRoomMessage message)  {
        //TODO 请在这里写下你的代码

    }

    @Override
    public int handlerId() {
                //2018-12-28 10:57:04 2000102
        return CSJoinRoomMessage.MESSAGE_ID;
    }

    @Override
    public CSJoinRoomMessage getEmptyMessage() {
        return new CSJoinRoomMessage();
    }


    @Override
    public SCAskHandleMessage ask(CSAskHandleMessage message) {

        logger.info("询问是否能够加入房间 {}", message.getValue());

        return null;
    }
}