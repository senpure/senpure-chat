package com.senpure.chat.protocol.message;

import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-3-20 10:35:15
 */
public class SCErrorMessage extends Message {

    public static final int MESSAGE_ID = 2000105;
    private String message;

    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf) {
        getSerializedSize();
        if (message != null) {
            writeString(buf, 8, message);
        }
    }

    /**
     * 读取字节缓存
     */
    @Override
    public void read(ByteBuf buf, int endIndex) {
        while (true) {
            int tag = readTag(buf, endIndex);
            switch (tag) {
                case 0://end
                    return;
                case 8:// 1 << 3 | 0
                    message = readString(buf);
                    break;
                default://skip
                    skip(buf, tag);
                    break;
            }
        }
    }

    private int serializedSize = -1;

    @Override
    public int getSerializedSize() {
        int size = serializedSize;
        if (size != -1) {
            return size;
        }
        size = 0;
        if (message != null) {
            size += computeStringSize(1, message);
        }
        serializedSize = size;
        return size;
    }

    public String getMessage() {
        return message;
    }

    public SCErrorMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public int getMessageId() {
        return 2000105;
    }

    @Override
    public String toString() {
        return "SCErrorMessage[2000105]{"
                + "message=" + message
                + "}";
    }

    //最长字段长度 6
    private int filedPad = 6;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("SCErrorMessage").append("[2000105]").append("{");
        sb.append("\n");
        sb.append(indent).append(rightPad("message", filedPad)).append(" = ").append(message);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}