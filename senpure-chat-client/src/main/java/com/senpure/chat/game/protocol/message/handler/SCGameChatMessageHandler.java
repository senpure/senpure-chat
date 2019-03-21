package com.senpure.chat.game.protocol.message.handler;

import com.senpure.base.util.DateFormatUtil;
import com.senpure.chat.client.ui.view.ClientController;
import com.senpure.chat.game.protocol.message.SCGameChatMessage;
import com.senpure.io.handler.AbstractMessageHandler;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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


        StringBuilder out = new StringBuilder();
        out.append(message.getTitle())
                .append("   ");
        out.append(DateFormatUtil.format(new Date())).append(newLine);
        //cut(out, message.getContent());
        out.append(message.getContent());
        // clientController.message(message.getTitle() + "  " + DateFormatUtil.format(new Date()) + ":\n" + message.getContent() + "\n-------------------------\n");
        clientController.message(out.toString());

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

    private static int lineLen = 70;
    private static String newLine = "\n";
    private static String prefix = "║";
    //private static String suffix = "║";
    private static String suffix = "";

    private static String normal = "═";

    private static String space = " ";

    private static void append(StringBuilder out, String str, int size) {
        for (int i = 0; i < size; i++) {
            out.append(str);
        }
    }


    public static void cut(StringBuilder out, String str) {

        out.append("╔");
        append(out, normal, lineLen / 2);
        // out.append("╗").append(newLine);
        out.append(newLine);
        out.append(prefix);
        int len = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '\n') {
                // append(out, space, lineLen - len);
                out.append(suffix).append(newLine).append(prefix);
                len = 0;
                continue;
            }
            len += getLen(c);
            if (len == lineLen) {
                out.append(c);
                if (i < str.length() - 1) {
                    out.append(suffix).append(newLine).append(prefix);
                    len = 0;
                }
            } else if (len > lineLen) {
                out.append(suffix).append(newLine).append(prefix);
                out.append(c);
                len = 2;
            } else {
                out.append(c);
            }
        }
        if (len > 0) {
            //System.out.println(len);
            append(out, " ", lineLen - len);
            //
            // out.append(suffix);

            //out.append("|");
        }
        out.append(newLine);
        out.append("╚");
        append(out, normal, lineLen / 2);
        // out.append("╝").append(newLine);
        out.append(newLine);


    }


    private static int getLen(char c) {
        return c > 0x80 ? 2 : 1;
    }


    public static void main(String[] args) {

        String str = "789";
        StringBuilder out = new StringBuilder();
        cut(out, str);
        System.out.println(out);
        System.out.println(getLen('╚'));
    }

}

