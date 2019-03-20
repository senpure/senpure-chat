package com.senpure.chat.data.entity;

import com.senpure.base.entity.LongAndVersionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * User
 *
 * @author senpure
 * @time 2018-12-26 10:49:53
 */
@Entity
@Table(name = "user")
public class User extends LongAndVersionEntity {

    /**
     * 昵称
     */
    private String nick;
    /**
     * 钻石
     */
    private Long diamond;
    /**
     * 登录时间 时间戳
     */
    private Long loginTime;
    /**
     * 登录时间
     */
    private Date loginDate;
    private String strId;
    private Long gatewayToken;

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
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

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public User setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
        return this;
    }

    public void setDiamond(Long diamond) {
        this.diamond = diamond;
    }

    public Long getGatewayToken() {
        return gatewayToken;
    }

    public void setGatewayToken(Long gatewayToken) {
        this.gatewayToken = gatewayToken;
    }
}
