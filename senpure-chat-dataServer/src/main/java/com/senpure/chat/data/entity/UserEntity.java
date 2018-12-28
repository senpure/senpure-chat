package com.senpure.chat.data.entity;

/**
 * UserEntity
 *
 * @author senpure
 * @time 2018-12-26 10:49:53
 */
public class UserEntity {

    //用户id
    private long userId;
    //昵称
    private String nick;
    //钻石数
    private long diamond;
    private long loginTime;
    private String strId;

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public long getDiamond() {
        return diamond;
    }

    public void setDiamond(long diamond) {
        this.diamond = diamond;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }
}
