package com.senpure.chat.protocol.event;

import com.senpure.chat.protocol.bean.User;
import com.senpure.io.protocol.Event;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-2-15 15:05:55
 */
public class UserLoginEvent extends  Event {

    public static final int EVENT_ID = 100100;
    //登录时间
    private long time;
    //user
    private User user;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        //登录时间
        writeVar64(buf,8,time);
        //user
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
                //登录时间
                case 8:// 1 << 3 | 0
                        time = readVar64(buf);
                    break;
                //user
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
        //登录时间
        size += computeVar64Size(1,time);
        //user
        if (user != null){
            size += computeBeanSize(1,user);
        }
        serializedSize = size ;
        return size ;
    }

    /**
     * get 登录时间
     * @return
     */
    public  long getTime() {
        return time;
    }

    /**
     * set 登录时间
     */
    public UserLoginEvent setTime(long time) {
        this.time=time;
        return this;
    }
    /**
     * get user
     * @return
     */
    public  User getUser() {
        return user;
    }

    /**
     * set user
     */
    public UserLoginEvent setUser(User user) {
        this.user=user;
        return this;
    }

    @Override
    public int getEventId() {
        return 100100;
    }

    @Override
    public String toString() {
        return "UserLoginEvent[100100]{"
                +"time=" + time
                +",user=" + user
                + "}";
   }

    //4 + 3 = 7 个空格
    private String nextIndent ="       ";
    //最长字段长度 4
    private int filedPad = 4;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("UserLoginEvent").append("[100100]").append("{");
        //登录时间
        sb.append("\n");
        sb.append(indent).append(rightPad("time", filedPad)).append(" = ").append(time);
        //user
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