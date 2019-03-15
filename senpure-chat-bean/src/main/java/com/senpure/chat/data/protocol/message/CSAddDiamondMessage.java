package com.senpure.chat.data.protocol.message;

import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-2-15 15:05:55
 */
public class CSAddDiamondMessage extends  Message {

    public static final int MESSAGE_ID = 1000105;
    private long id;
    private String type;
    private long diamond;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        writeVar64(buf,8,id);
        if (type != null){
            writeString(buf,16,type);
        }
        writeVar64(buf,24,diamond);
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
                case 16:// 2 << 3 | 0
                        type = readString(buf);
                    break;
                case 24:// 3 << 3 | 0
                        diamond = readVar64(buf);
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
        if (type != null){
            size += computeStringSize(1,type);
        }
        size += computeVar64Size(1,diamond);
        serializedSize = size ;
        return size ;
    }

    public  long getId() {
        return id;
    }

    public CSAddDiamondMessage setId(long id) {
        this.id=id;
        return this;
    }
    public  String getType() {
        return type;
    }

    public CSAddDiamondMessage setType(String type) {
        this.type=type;
        return this;
    }
    public  long getDiamond() {
        return diamond;
    }

    public CSAddDiamondMessage setDiamond(long diamond) {
        this.diamond=diamond;
        return this;
    }

    @Override
    public int getMessageId() {
        return 1000105;
    }

    @Override
    public String toString() {
        return "CSAddDiamondMessage[1000105]{"
                +"id=" + id
                +",type=" + type
                +",diamond=" + diamond
                + "}";
   }

    //最长字段长度 7
    private int filedPad = 7;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("CSAddDiamondMessage").append("[1000105]").append("{");
        sb.append("\n");
        sb.append(indent).append(rightPad("id", filedPad)).append(" = ").append(id);
        sb.append("\n");
        sb.append(indent).append(rightPad("type", filedPad)).append(" = ").append(type);
        sb.append("\n");
        sb.append(indent).append(rightPad("diamond", filedPad)).append(" = ").append(diamond);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}