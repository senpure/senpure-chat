package com.senpure.chat.data.criteria;

import com.senpure.base.criterion.Criteria;
import com.senpure.chat.data.model.User;
import com.senpure.base.util.DateFormatUtil;

import java.util.Date;
import java.io.Serializable;

/**
 * User
 *
 * @author senpure-generator
 * @version 2019-3-20 14:12:43
 */
public class UserCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 274272385L;

    //(主键)
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //昵称
    private String nick;
    //table [user][column = nick] criteriaOrder
    private String nickOrder;
    //钻石
    private Long diamond;
    //table [user][column = diamond] criteriaOrder
    private String diamondOrder;
    //登录时间
    private Date loginDate;
    private Date startLoginDate;
    private Date endLoginDate;
    //table [user][column = login_date] criteriaOrder
    private String loginDateOrder;
    private String strId;
    //table [user][column = str_id] criteriaOrder
    private String strIdOrder;
    private Long gatewayToken;

    public static User toUser(UserCriteria criteria, User user) {
        user.setId(criteria.getId());
        user.setNick(criteria.getNick());
        user.setDiamond(criteria.getDiamond());
        user.setLoginDate(criteria.getLoginDate());
        if (criteria.getLoginDate() != null) {
            user.setLoginTime(criteria.getLoginDate().getTime());
        }
        user.setStrId(criteria.getStrId());
        user.setGatewayToken(criteria.getGatewayToken());
        user.setVersion(criteria.getVersion());
        return user;
    }

    public User toUser() {
        User user = new User();
        return toUser(this, user);
    }

    /**
     * 将UserCriteria 的有效值(不为空),赋值给 User
     *
     * @return User
     */
    public User effective(User user) {
        if (getId() != null) {
            user.setId(getId());
        }
        if (getNick() != null) {
            user.setNick(getNick());
        }
        if (getDiamond() != null) {
            user.setDiamond(getDiamond());
        }
        if (getLoginDate() != null) {
            user.setLoginDate(getLoginDate());
            user.setLoginTime(getLoginDate().getTime());
        }
        if (getStrId() != null) {
            user.setStrId(getStrId());
        }
        if (getGatewayToken() != null) {
            user.setGatewayToken(getGatewayToken());
        }
        if (getVersion() != null) {
            user.setVersion(getVersion());
        }
        return user;
    }

    @Override
    protected void rangeStr(StringBuilder sb) {
        if (startLoginDate != null) {
            sb.append("startLoginDate=").append(DateFormatUtil.getDateFormat(datePattern).format(startLoginDate)).append(",");
        }
        if (endLoginDate != null) {
            sb.append("endLoginDate=").append(DateFormatUtil.getDateFormat(datePattern).format(endLoginDate)).append(",");
        }
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("UserCriteria{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (nick != null) {
            sb.append("nick=").append(nick).append(",");
        }
        if (diamond != null) {
            sb.append("diamond=").append(diamond).append(",");
        }
        if (loginDate != null) {
            sb.append("loginDate=").append(loginDate).append(",");
        }
        if (strId != null) {
            sb.append("strId=").append(strId).append(",");
        }
        if (gatewayToken != null) {
            sb.append("gatewayToken=").append(gatewayToken).append(",");
        }
    }

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
    public UserCriteria setId(Long id) {
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
    public UserCriteria setNick(String nick) {
        if (nick != null && nick.trim().length() == 0) {
            this.nick = null;
            return this;
        }
        this.nick = nick;
        return this;
    }

    /**
     * get table [user][column = nick] criteriaOrder
     *
     * @return
     */
    public String getNickOrder() {
        return nickOrder;
    }

    /**
     * set table [user][column = nick] criteriaOrder DESC||ASC
     *
     * @return
     */
    public UserCriteria setNickOrder(String nickOrder) {
        this.nickOrder = nickOrder;
        putSort("nick", nickOrder);
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
    public UserCriteria setDiamond(Long diamond) {
        this.diamond = diamond;
        return this;
    }

    /**
     * get table [user][column = diamond] criteriaOrder
     *
     * @return
     */
    public String getDiamondOrder() {
        return diamondOrder;
    }

    /**
     * set table [user][column = diamond] criteriaOrder DESC||ASC
     *
     * @return
     */
    public UserCriteria setDiamondOrder(String diamondOrder) {
        this.diamondOrder = diamondOrder;
        putSort("diamond", diamondOrder);
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
    public UserCriteria setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
        return this;
    }

    public Date getStartLoginDate() {
        return startLoginDate;
    }

    public UserCriteria setStartLoginDate(Date startLoginDate) {
        this.startLoginDate = startLoginDate;
        return this;
    }

    public Date getEndLoginDate() {
        return endLoginDate;
    }

    public UserCriteria setEndLoginDate(Date endLoginDate) {
        this.endLoginDate = endLoginDate;
        return this;
    }

    /**
     * get table [user][column = login_date] criteriaOrder
     *
     * @return
     */
    public String getLoginDateOrder() {
        return loginDateOrder;
    }

    /**
     * set table [user][column = login_date] criteriaOrder DESC||ASC
     *
     * @return
     */
    public UserCriteria setLoginDateOrder(String loginDateOrder) {
        this.loginDateOrder = loginDateOrder;
        putSort("login_time", loginDateOrder);
        return this;
    }

    public String getStrId() {
        return strId;
    }


    public UserCriteria setStrId(String strId) {
        if (strId != null && strId.trim().length() == 0) {
            this.strId = null;
            return this;
        }
        this.strId = strId;
        return this;
    }

    /**
     * get table [user][column = str_id] criteriaOrder
     *
     * @return
     */
    public String getStrIdOrder() {
        return strIdOrder;
    }

    /**
     * set table [user][column = str_id] criteriaOrder DESC||ASC
     *
     * @return
     */
    public UserCriteria setStrIdOrder(String strIdOrder) {
        this.strIdOrder = strIdOrder;
        putSort("str_id", strIdOrder);
        return this;
    }

    public Long getGatewayToken() {
        return gatewayToken;
    }


    public UserCriteria setGatewayToken(Long gatewayToken) {
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
    public UserCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}