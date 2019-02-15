package com.senpure.chat.data.protocol.message;

import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-2-15 15:05:55
 */
public class CSUserLoginMessage extends  Message {

    public static final int MESSAGE_ID = 1000100;
    private String id;
    private String nick;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        if (id != null){
            writeString(buf,8,id);
        }
        if (nick != null){
            writeString(buf,16,nick);
        }
    }

    /**
     * 读取字节缓存
     */
    @Override
    public void read(ByteBuf buf,int endIndex){
        while(true){
            int tag = readTag(buf, endIndex);
            switch (tag) {
                case 0://end
                return;
                case 8:// 1 << 3 | 0
                        id = readString(buf);
                    break;
                case 16:// 2 << 3 | 0
                        nick = readString(buf);
                    break;
                default://skip
                    skip(buf, tag);
                    break;
            }
        }
    }

    private int serializedSize = -1;

    @Override
    public int getSerializedSize(){
        int size = serializedSize ;
        if (size != -1 ){
            return size;
        }
        size = 0 ;
        if (id != null){
            size += computeStringSize(1,id);
        }
        if (nick != null){
            size += computeStringSize(1,nick);
        }
        serializedSize = size ;
        return size ;
    }

    public  String getId() {
        return id;
    }

    public CSUserLoginMessage setId(String id) {
        this.id=id;
        return this;
    }
    public  String getNick() {
        return nick;
    }

    public CSUserLoginMessage setNick(String nick) {
        this.nick=nick;
        return this;
    }

    @Override
    public int getMessageId() {
        return 1000100;
    }

    @Override
    public String toString() {
        return "CSUserLoginMessage[1000100]{"
                +"id=" + id
                +",nick=" + nick
                + "}";
   }

    //最长字段长度 4
    private int filedPad = 4;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("CSUserLoginMessage").append("[1000100]").append("{");
        sb.append("\n");
        sb.append(indent).append(rightPad("id", filedPad)).append(" = ").append(id);
        sb.append("\n");
        sb.append(indent).append(rightPad("nick", filedPad)).append(" = ").append(nick);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}