package com.senpure.chat.game.protocol.message;

import com.senpure.chat.protocol.bean.User;
import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-3-20 10:11:12
 */
public class SCExitGameChatMessage extends  Message {

    public static final int MESSAGE_ID = 4000202;
    private int roomId;
    private User user;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        writeVar32(buf,8,roomId);
        if (user!= null){
            writeBean(buf,19,user);
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
                        roomId = readVar32(buf);
                    break;
                case 19:// 2 << 3 | 3
                user = new User();
                        readBean(buf,user);
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
        size += computeVar32Size(1,roomId);
        if (user != null){
            size += computeBeanSize(1,user);
        }
        serializedSize = size ;
        return size ;
    }

    public  int getRoomId() {
        return roomId;
    }

    public SCExitGameChatMessage setRoomId(int roomId) {
        this.roomId=roomId;
        return this;
    }
    public  User getUser() {
        return user;
    }

    public SCExitGameChatMessage setUser(User user) {
        this.user=user;
        return this;
    }

    @Override
    public int getMessageId() {
        return 4000202;
    }

    @Override
    public String toString() {
        return "SCExitGameChatMessage[4000202]{"
                +"roomId=" + roomId
                +",user=" + user
                + "}";
   }

    //6 + 3 = 9 个空格
    private String nextIndent ="         ";
    //最长字段长度 6
    private int filedPad = 6;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("SCExitGameChatMessage").append("[4000202]").append("{");
        sb.append("\n");
        sb.append(indent).append(rightPad("roomId", filedPad)).append(" = ").append(roomId);
        sb.append("\n");
        sb.append(indent).append(rightPad("user", filedPad)).append(" = ");
        if(user!=null){
            sb.append(user.toString(indent+nextIndent));
        } else {
            sb.append("null");
        }
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}