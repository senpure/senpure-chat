package com.senpure.chat.data.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 * 
 * @author senpure-generator
 * @version 2019-3-20 14:12:43
 */
@ApiModel(description = "User")
public class User implements Serializable {
    private static final long serialVersionUID = 274272385L;

    //(主键)
    private Long id;
    //乐观锁，版本控制
    @ApiModelProperty(hidden = true )
    private Integer version;
    //昵称
    @ApiModelProperty(value = "昵称", example = "nick", position = 1)
    private String nick;
    //钻石
    @ApiModelProperty(value = "钻石", dataType = "long", example = "666666", position = 2)
    private Long diamond;
    //登录时间 时间戳
    @ApiModelProperty(value = "登录时间 时间戳", dataType = "long", example = "1553011200000", position = 3)
    private Long loginTime;
    //登录时间
    @ApiModelProperty(value = "登录时间", dataType = "date-time", example = "2019-03-20 00:00:00", position = 4)
    private Date loginDate;
    @ApiModelProperty(example = "strId", position = 5)
    private String strId;
    @ApiModelProperty(dataType = "long", example = "666666", position = 6)
    private Long gatewayToken;

    /**
     * get (主键)
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * set (主键)
     *
     * @return
     */
    public User setId(Long id) {
        this.id = id;
        return this;
    }


    /**
     * get 昵称
     *
     * @return
     */
    public String getNick() {
        return nick;
    }

    /**
     * set 昵称
     *
     * @return
     */
    public User setNick(String nick) {
        this.nick = nick;
        return this;
    }


    /**
     * get 钻石
     *
     * @return
     */
    public Long getDiamond() {
        return diamond;
    }

    /**
     * set 钻石
     *
     * @return
     */
    public User setDiamond(Long diamond) {
        this.diamond = diamond;
        return this;
    }


    /**
     * get 登录时间 时间戳
     *
     * @return
     */
    public Long getLoginTime() {
        return loginTime;
    }

    /**
     * set 登录时间 时间戳
     *
     * @return
     */
    public User setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
        return this;
    }


    /**
     * get 登录时间
     *
     * @return
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * set 登录时间
     *
     * @return
     */
    public User setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
        return this;
    }


    public String getStrId() {
        return strId;
    }


    public User setStrId(String strId) {
        this.strId = strId;
        return this;
    }


    public Long getGatewayToken() {
        return gatewayToken;
    }


    public User setGatewayToken(Long gatewayToken) {
        this.gatewayToken = gatewayToken;
        return this;
    }


    /**
     * get 乐观锁，版本控制
     *
     * @return
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * set 乐观锁，版本控制
     *
     * @return
     */
    public User setVersion(Integer version) {
        this.version = version;
        return this;
    }


    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ",version=" + version
                + ",nick=" + nick
                + ",diamond=" + diamond
                + ",loginTime=" + loginTime
                + ",loginDate=" + loginDate
                + ",strId=" + strId
                + ",gatewayToken=" + gatewayToken
                + "}";
    }

}