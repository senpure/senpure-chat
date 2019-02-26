package com.senpure.chat.free.logic;

/**
 * Player
 *
 * @author senpure
 * @time 2018-12-28 11:45:15
 */
public class Player {

    //用户id
    private long id;
    //昵称
    private String nick;
    //钻石数
    private long diamond;
    private long token;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getToken() {
        return token;
    }

    public Player setToken(long token) {
        this.token = token;
        return this;
    }
}
