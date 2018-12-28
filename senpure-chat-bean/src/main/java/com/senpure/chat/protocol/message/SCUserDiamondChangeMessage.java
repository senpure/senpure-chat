package com.senpure.chat.protocol.message;

import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2018-12-28 10:57:04
 */
public class SCUserDiamondChangeMessage extends  Message {

    public static final int MESSAGE_ID = 2000104;
    private long userId;
    private long changeDiamond;
    private long afterDiamond;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        writeVar64(buf,8,userId);
        writeVar64(buf,16,changeDiamond);
        writeVar64(buf,24,afterDiamond);
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
                        changeDiamond = readVar64(buf);
                    break;
                case 24:// 3 << 3 | 0
                        afterDiamond = readVar64(buf);
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
        size += computeVar64Size(1,changeDiamond);
        size += computeVar64Size(1,afterDiamond);
        serializedSize = size ;
        return size ;
    }

    public  long getUserId() {
        return userId;
    }

    public SCUserDiamondChangeMessage setUserId(long userId) {
        this.userId=userId;
        return this;
    }
    public  long getChangeDiamond() {
        return changeDiamond;
    }

    public SCUserDiamondChangeMessage setChangeDiamond(long changeDiamond) {
        this.changeDiamond=changeDiamond;
        return this;
    }
    public  long getAfterDiamond() {
        return afterDiamond;
    }

    public SCUserDiamondChangeMessage setAfterDiamond(long afterDiamond) {
        this.afterDiamond=afterDiamond;
        return this;
    }

    @Override
    public int getMessageId() {
        return 2000104;
    }

    @Override
    public String toString() {
        return "SCUserDiamondChangeMessage[2000104]{"
                +"userId=" + userId
                +",changeDiamond=" + changeDiamond
                +",afterDiamond=" + afterDiamond
                + "}";
   }

    //最长字段长度 13
    private int filedPad = 13;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("SCUserDiamondChangeMessage").append("[2000104]").append("{");
        sb.append("\n");
        sb.append(indent).append(rightPad("userId", filedPad)).append(" = ").append(userId);
        sb.append("\n");
        sb.append(indent).append(rightPad("changeDiamond", filedPad)).append(" = ").append(changeDiamond);
        sb.append("\n");
        sb.append(indent).append(rightPad("afterDiamond", filedPad)).append(" = ").append(afterDiamond);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}