package com.senpure.chat.client.ui.view;

import com.senpure.chat.protocol.message.CSJoinRoomMessage;
import com.senpure.io.protocol.Bean;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * ClientControllerTest
 *
 * @author senpure
 * @time 2019-02-15 16:18:30
 */
public class ClientControllerTest {

    @Test
    public void login() {

        CSJoinRoomMessage message = new CSJoinRoomMessage();
        message.setRoomId("567");

        ByteBuf buf = Unpooled.buffer();
        message.write(buf);
        CSJoinRoomMessage mess = new CSJoinRoomMessage();
        Bean.readTag(buf, buf.writerIndex());
        mess.read(buf, buf.writerIndex());
        System.out.println(mess);

    }
}