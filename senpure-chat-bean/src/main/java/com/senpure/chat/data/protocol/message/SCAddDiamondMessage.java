package com.senpure.chat.data.protocol.message;

import com.senpure.chat.protocol.bean.User;
import com.senpure.io.protocol.Message;
import io.netty.buffer.ByteBuf;

/**
 * @author senpure
 * @time 2019-2-15 15:05:55
 */
public class SCAddDiamondMessage extends  Message {

    public static final int MESSAGE_ID = 1000106;
    private String type;
    private long diamond;
    private User user;
    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        getSerializedSize();
        if (type != null){
            writeString(buf,8,type);
        }
        writeVar64(buf,16,diamond);
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
                case 8:// 1 << 3 | 0
                        type = readString(buf);
                    break;
                case 16:// 2 << 3 | 0
                        diamond = readVar64(buf);
                    break;
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
        if (type != null){
            size += computeStringSize(1,type);
        }
        size += computeVar64Size(1,diamond);
        if (user != null){
            size += computeBeanSize(1,user);
        }
        serializedSize = size ;
        return size ;
    }

    public  String getType() {
        return type;
    }

    public SCAddDiamondMessage setType(String type) {
        this.type=type;
        return this;
    }
    public  long getDiamond() {
        return diamond;
    }

    public SCAddDiamondMessage setDiamond(long diamond) {
        this.diamond=diamond;
        return this;
    }
    public  User getUser() {
        return user;
    }

    public SCAddDiamondMessage setUser(User user) {
        this.user=user;
        return this;
    }

    @Override
    public int getMessageId() {
        return 1000106;
    }

    @Override
    public String toString() {
        return "SCAddDiamondMessage[1000106]{"
                +"type=" + type
                +",diamond=" + diamond
                +",user=" + user
                + "}";
   }

    //7 + 3 = 10 个空格
    private String nextIndent ="          ";
    //最长字段长度 7
    private int filedPad = 7;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("SCAddDiamondMessage").append("[1000106]").append("{");
        sb.append("\n");
        sb.append(indent).append(rightPad("type", filedPad)).append(" = ").append(type);
        sb.append("\n");
        sb.append(indent).append(rightPad("diamond", filedPad)).append(" = ").append(diamond);
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