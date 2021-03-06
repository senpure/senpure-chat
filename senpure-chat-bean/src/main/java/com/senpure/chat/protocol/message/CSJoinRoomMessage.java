package com.senpure.chat.protocol.message;

import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-3-20 10:35:15
 */
public class CSJoinRoomMessage extends  Message {

    public static final int MESSAGE_ID = 2000102;
    private String roomId;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        if (roomId != null){
            writeString(buf,8,roomId);
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
                        roomId = readString(buf);
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
        if (roomId != null){
            size += computeStringSize(1,roomId);
        }
        serializedSize = size ;
        return size ;
    }

    public  String getRoomId() {
        return roomId;
    }

    public CSJoinRoomMessage setRoomId(String roomId) {
        this.roomId=roomId;
        return this;
    }

    @Override
    public int getMessageId() {
        return 2000102;
    }

    @Override
    public String toString() {
        return "CSJoinRoomMessage[2000102]{"
                +"roomId=" + roomId
                + "}";
   }

    //最长字段长度 6
    private int filedPad = 6;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("CSJoinRoomMessage").append("[2000102]").append("{");
        sb.append("\n");
        sb.append(indent).append(rightPad("roomId", filedPad)).append(" = ").append(roomId);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}