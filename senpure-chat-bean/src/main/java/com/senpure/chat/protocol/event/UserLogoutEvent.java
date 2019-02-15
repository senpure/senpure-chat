package com.senpure.chat.protocol.event;

import com.senpure.chat.protocol.bean.User;
import com.senpure.io.protocol.Event;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-2-15 15:05:55
 */
public class UserLogoutEvent extends  Event {

    public static final int EVENT_ID = 100101;
    //登录时间
    private long loginTime;
    //退出时间
    private long logoutTime;
    //user
    private User user;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        //登录时间
        writeVar64(buf,8,loginTime);
        //退出时间
        writeVar64(buf,16,logoutTime);
        //user
        if (user!= null){
            writeBean(buf,27,user);
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
                        loginTime = readVar64(buf);
                    break;
                //退出时间
                case 16:// 2 << 3 | 0
                        logoutTime = readVar64(buf);
                    break;
                //user
                case 27:// 3 << 3 | 3
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
        size += computeVar64Size(1,loginTime);
        //退出时间
        size += computeVar64Size(1,logoutTime);
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
    public  long getLoginTime() {
        return loginTime;
    }

    /**
     * set 登录时间
     */
    public UserLogoutEvent setLoginTime(long loginTime) {
        this.loginTime=loginTime;
        return this;
    }
    /**
     * get 退出时间
     * @return
     */
    public  long getLogoutTime() {
        return logoutTime;
    }

    /**
     * set 退出时间
     */
    public UserLogoutEvent setLogoutTime(long logoutTime) {
        this.logoutTime=logoutTime;
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
    public UserLogoutEvent setUser(User user) {
        this.user=user;
        return this;
    }

    @Override
    public int getEventId() {
        return 100101;
    }

    @Override
    public String toString() {
        return "UserLogoutEvent[100101]{"
                +"loginTime=" + loginTime
                +",logoutTime=" + logoutTime
                +",user=" + user
                + "}";
   }

    //10 + 3 = 13 个空格
    private String nextIndent ="             ";
    //最长字段长度 10
    private int filedPad = 10;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("UserLogoutEvent").append("[100101]").append("{");
        //登录时间
        sb.append("\n");
        sb.append(indent).append(rightPad("loginTime", filedPad)).append(" = ").append(loginTime);
        //退出时间
        sb.append("\n");
        sb.append(indent).append(rightPad("logoutTime", filedPad)).append(" = ").append(logoutTime);
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