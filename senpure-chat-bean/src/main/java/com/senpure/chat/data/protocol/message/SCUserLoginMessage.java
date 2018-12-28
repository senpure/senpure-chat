package com.senpure.chat.data.protocol.message;

import com.senpure.chat.protocol.bean.User;
import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2018-12-28 10:57:25
 */
public class SCUserLoginMessage extends  Message {

    public static final int MESSAGE_ID = 1000101;
    private User user;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        if (user!= null){
            writeBean(buf,11,user);
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
                case 11:// 1 << 3 | 3
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
        if (user != null){
            size += computeBeanSize(1,user);
        }
        serializedSize = size ;
        return size ;
    }

    public  User getUser() {
        return user;
    }

    public SCUserLoginMessage setUser(User user) {
        this.user=user;
        return this;
    }

    @Override
    public int getMessageId() {
        return 1000101;
    }

    @Override
    public String toString() {
        return "SCUserLoginMessage[1000101]{"
                +"user=" + user
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
        sb.append("SCUserLoginMessage").append("[1000101]").append("{");
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