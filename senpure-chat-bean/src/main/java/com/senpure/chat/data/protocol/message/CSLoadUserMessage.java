package com.senpure.chat.data.protocol.message;

import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-2-15 15:05:55
 */
public class CSLoadUserMessage extends  Message {

    public static final int MESSAGE_ID = 1000103;
    private long id;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        writeVar64(buf,8,id);
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
                        id = readVar64(buf);
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
        size += computeVar64Size(1,id);
        serializedSize = size ;
        return size ;
    }

    public  long getId() {
        return id;
    }

    public CSLoadUserMessage setId(long id) {
        this.id=id;
        return this;
    }

    @Override
    public int getMessageId() {
        return 1000103;
    }

    @Override
    public String toString() {
        return "CSLoadUserMessage[1000103]{"
                +"id=" + id
                + "}";
   }

    //最长字段长度 2
    private int filedPad = 2;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("CSLoadUserMessage").append("[1000103]").append("{");
        sb.append("\n");
        sb.append(indent).append(rightPad("id", filedPad)).append(" = ").append(id);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}