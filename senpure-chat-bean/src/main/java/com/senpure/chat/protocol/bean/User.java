package com.senpure.chat.protocol.bean;

import com.senpure.io.protocol.Bean;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2018-12-28 10:54:29
 */
public class User extends  Bean {
    //用户id
    private long userId;
    //昵称
    private String nick;
    //钻石数
    private long diamond;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        //用户id
        writeVar64(buf,8,userId);
        //昵称
        if (nick != null){
            writeString(buf,16,nick);
        }
        //钻石数
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
                //用户id
                case 8:// 1 << 3 | 0
                        userId = readVar64(buf);
                    break;
                //昵称
                case 16:// 2 << 3 | 0
                        nick = readString(buf);
                    break;
                //钻石数
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
        //用户id
        size += computeVar64Size(1,userId);
        //昵称
        if (nick != null){
            size += computeStringSize(1,nick);
        }
        //钻石数
        size += computeVar64Size(1,diamond);
        serializedSize = size ;
        return size ;
    }

    /**
     * get 用户id
     * @return
     */
    public  long getUserId() {
        return userId;
    }

    /**
     * set 用户id
     */
    public User setUserId(long userId) {
        this.userId=userId;
        return this;
    }
    /**
     * get 昵称
     * @return
     */
    public  String getNick() {
        return nick;
    }

    /**
     * set 昵称
     */
    public User setNick(String nick) {
        this.nick=nick;
        return this;
    }
    /**
     * get 钻石数
     * @return
     */
    public  long getDiamond() {
        return diamond;
    }

    /**
     * set 钻石数
     */
    public User setDiamond(long diamond) {
        this.diamond=diamond;
        return this;
    }

    @Override
    public String toString() {
        return "User{"
                +"userId=" + userId
                +",nick=" + nick
                +",diamond=" + diamond
                + "}";
   }

    //最长字段长度 7
    private int filedPad = 7;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("User").append("{");
        //用户id
        sb.append("\n");
        sb.append(indent).append(rightPad("userId", filedPad)).append(" = ").append(userId);
        //昵称
        sb.append("\n");
        sb.append(indent).append(rightPad("nick", filedPad)).append(" = ").append(nick);
        //钻石数
        sb.append("\n");
        sb.append(indent).append(rightPad("diamond", filedPad)).append(" = ").append(diamond);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}