package com.senpure.chat.free.protocol.message;

import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-2-15 15:05:55
 */
public class SCFreeChatMessage extends  Message {

    public static final int MESSAGE_ID = 3000101;
    private long userId;
    private String type;
    private String title;
    private String content;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        writeVar64(buf,8,userId);
        if (type != null){
            writeString(buf,16,type);
        }
        if (title != null){
            writeString(buf,24,title);
        }
        if (content != null){
            writeString(buf,32,content);
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
                        userId = readVar64(buf);
                    break;
                case 16:// 2 << 3 | 0
                        type = readString(buf);
                    break;
                case 24:// 3 << 3 | 0
                        title = readString(buf);
                    break;
                case 32:// 4 << 3 | 0
                        content = readString(buf);
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
        size += computeVar64Size(1,userId);
        if (type != null){
            size += computeStringSize(1,type);
        }
        if (title != null){
            size += computeStringSize(1,title);
        }
        if (content != null){
            size += computeStringSize(1,content);
        }
        serializedSize = size ;
        return size ;
    }

    public  long getUserId() {
        return userId;
    }

    public SCFreeChatMessage setUserId(long userId) {
        this.userId=userId;
        return this;
    }
    public  String getType() {
        return type;
    }

    public SCFreeChatMessage setType(String type) {
        this.type=type;
        return this;
    }
    public  String getTitle() {
        return title;
    }

    public SCFreeChatMessage setTitle(String title) {
        this.title=title;
        return this;
    }
    public  String getContent() {
        return content;
    }

    public SCFreeChatMessage setContent(String content) {
        this.content=content;
        return this;
    }

    @Override
    public int getMessageId() {
        return 3000101;
    }

    @Override
    public String toString() {
        return "SCFreeChatMessage[3000101]{"
                +"userId=" + userId
                +",type=" + type
                +",title=" + title
                +",content=" + content
                + "}";
   }

    //最长字段长度 7
    private int filedPad = 7;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("SCFreeChatMessage").append("[3000101]").append("{");
        sb.append("\n");
        sb.append(indent).append(rightPad("userId", filedPad)).append(" = ").append(userId);
        sb.append("\n");
        sb.append(indent).append(rightPad("type", filedPad)).append(" = ").append(type);
        sb.append("\n");
        sb.append(indent).append(rightPad("title", filedPad)).append(" = ").append(title);
        sb.append("\n");
        sb.append(indent).append(rightPad("content", filedPad)).append(" = ").append(content);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}