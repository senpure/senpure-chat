package com.senpure.chat.protocol.event;

import com.senpure.chat.protocol.bean.User;
import com.senpure.io.protocol.Event;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2018-12-28 10:57:25
 */
public class UserDiamondChangeEvent extends  Event {

    public static final int EVENT_ID = 100102;
    //改变方式
    private String changeType;
    //时间
    private long time;
    //before
    private long before;
    //num
    private long num;
    //after
    private long after;
    //user
    private User user;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        //改变方式
        if (changeType != null){
            writeString(buf,8,changeType);
        }
        //时间
        writeVar64(buf,16,time);
        //before
        writeVar64(buf,24,before);
        //num
        writeVar64(buf,32,num);
        //after
        writeVar64(buf,40,after);
        //user
        if (user!= null){
            writeBean(buf,51,user);
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
                //改变方式
                case 8:// 1 << 3 | 0
                        changeType = readString(buf);
                    break;
                //时间
                case 16:// 2 << 3 | 0
                        time = readVar64(buf);
                    break;
                //before
                case 24:// 3 << 3 | 0
                        before = readVar64(buf);
                    break;
                //num
                case 32:// 4 << 3 | 0
                        num = readVar64(buf);
                    break;
                //after
                case 40:// 5 << 3 | 0
                        after = readVar64(buf);
                    break;
                //user
                case 51:// 6 << 3 | 3
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
        //改变方式
        if (changeType != null){
            size += computeStringSize(1,changeType);
        }
        //时间
        size += computeVar64Size(1,time);
        //before
        size += computeVar64Size(1,before);
        //num
        size += computeVar64Size(1,num);
        //after
        size += computeVar64Size(1,after);
        //user
        if (user != null){
            size += computeBeanSize(1,user);
        }
        serializedSize = size ;
        return size ;
    }

    /**
     * get 改变方式
     * @return
     */
    public  String getChangeType() {
        return changeType;
    }

    /**
     * set 改变方式
     */
    public UserDiamondChangeEvent setChangeType(String changeType) {
        this.changeType=changeType;
        return this;
    }
    /**
     * get 时间
     * @return
     */
    public  long getTime() {
        return time;
    }

    /**
     * set 时间
     */
    public UserDiamondChangeEvent setTime(long time) {
        this.time=time;
        return this;
    }
    /**
     * get before
     * @return
     */
    public  long getBefore() {
        return before;
    }

    /**
     * set before
     */
    public UserDiamondChangeEvent setBefore(long before) {
        this.before=before;
        return this;
    }
    /**
     * get num
     * @return
     */
    public  long getNum() {
        return num;
    }

    /**
     * set num
     */
    public UserDiamondChangeEvent setNum(long num) {
        this.num=num;
        return this;
    }
    /**
     * get after
     * @return
     */
    public  long getAfter() {
        return after;
    }

    /**
     * set after
     */
    public UserDiamondChangeEvent setAfter(long after) {
        this.after=after;
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
    public UserDiamondChangeEvent setUser(User user) {
        this.user=user;
        return this;
    }

    @Override
    public int getEventId() {
        return 100102;
    }

    @Override
    public String toString() {
        return "UserDiamondChangeEvent[100102]{"
                +"changeType=" + changeType
                +",time=" + time
                +",before=" + before
                +",num=" + num
                +",after=" + after
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
        sb.append("UserDiamondChangeEvent").append("[100102]").append("{");
        //改变方式
        sb.append("\n");
        sb.append(indent).append(rightPad("changeType", filedPad)).append(" = ").append(changeType);
        //时间
        sb.append("\n");
        sb.append(indent).append(rightPad("time", filedPad)).append(" = ").append(time);
        //before
        sb.append("\n");
        sb.append(indent).append(rightPad("before", filedPad)).append(" = ").append(before);
        //num
        sb.append("\n");
        sb.append(indent).append(rightPad("num", filedPad)).append(" = ").append(num);
        //after
        sb.append("\n");
        sb.append(indent).append(rightPad("after", filedPad)).append(" = ").append(after);
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