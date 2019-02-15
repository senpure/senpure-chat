package com.senpure.chat.free.protocol.message;

import com.senpure.chat.protocol.bean.User;
import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-2-15 15:05:55
 */
public class SCExitFreeChatMessage extends  Message {

    public static final int MESSAGE_ID = 3000202;
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

    public SCExitFreeChatMessage setUser(User user) {
        this.user=user;
        return this;
    }

    @Override
    public int getMessageId() {
        return 3000202;
    }

    @Override
    public String toString() {
        return "SCExitFreeChatMessage[3000202]{"
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
        sb.append("SCExitFreeChatMessage").append("[3000202]").append("{");
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