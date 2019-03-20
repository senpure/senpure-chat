package com.senpure.chat.data.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * User
 *
 * @author senpure-generator
 * @version 2019-3-20 14:12:43
 */
public class UserCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 274272385L;

    //(主键)
    @ApiModelProperty(value = "(主键)", dataType = "long", example = "666666", position = 7)
    private String id;
    //乐观锁，版本控制
    @ApiModelProperty(hidden = true )
    private String version;
    //昵称
    @ApiModelProperty(value = "昵称", example = "nick", position = 8)
    private String nick;
    //钻石
    @ApiModelProperty(value = "钻石", dataType = "long", example = "666666", position = 9)
    private String diamond;
    //登录时间
    @ApiModelProperty(value = "登录时间", dataType = "date-time", example = "2019-03-20 00:00:00", position = 10)
    private String loginDate;
    //loginDate 时间格式
    @ApiModelProperty(value = "loginDate 格式", example = "yyyy-MM-dd HH:mm:ss", position = 11)
    private String loginDatePattern ;
    @DynamicDate
    private PatternDate loginDateValid = new PatternDate();
    @ApiModelProperty(example = "strId", position = 12)
    private String strId;
    @ApiModelProperty(dataType = "long", example = "666666", position = 13)
    private String gatewayToken;
    @ApiModelProperty(value = "loginDate 开始范围 (>=)", dataType = "date-time", example = "2019-03-20 00:00:00", position = 14)
    private String startLoginDate;
    @ApiModelProperty(value = "loginDate 结束范围 (<=)", dataType = "date-time", example = "2019-03-20 23:59:59", position = 15)
    private String endLoginDate;
    @DynamicDate
    private PatternDate startLoginDateValid = new PatternDate();
    @DynamicDate
    private PatternDate endLoginDateValid = new PatternDate();
    //table [user][column = nick] criteriaOrder
    @ApiModelProperty(value = "nick 排序" , allowableValues = "ASC,DESC", position = 16)
    private String nickOrder;
    //table [user][column = diamond] criteriaOrder
    @ApiModelProperty(value = "diamond 排序" , allowableValues = "ASC,DESC", position = 17)
    private String diamondOrder;
    //table [user][column = login_time] criteriaOrder
    @ApiModelProperty(value = "loginDate 排序" , allowableValues = "ASC,DESC", position = 18)
    private String loginDateOrder;
    //table [user][column = str_id] criteriaOrder
    @ApiModelProperty(value = "strId 排序" , allowableValues = "ASC,DESC", position = 19)
    private String strIdOrder;

    public UserCriteria toUserCriteria() {
        UserCriteria criteria = new UserCriteria();
        criteria.setPage(Integer.valueOf(getPage()));
        criteria.setPageSize(Integer.valueOf(getPageSize()));
        //(主键)
        if (id != null) {
            criteria.setId(Long.valueOf(id));
        }
        //乐观锁，版本控制
        if (version != null) {
            criteria.setVersion(Integer.valueOf(version));
        }
        //昵称
        if (nick != null) {
            criteria.setNick(nick);
        }
        //table [user][column = nick] criteriaOrder
        if (nickOrder != null) {
            criteria.setNickOrder(nickOrder);
        }
        //钻石
        if (diamond != null) {
            criteria.setDiamond(Long.valueOf(diamond));
        }
        //table [user][column = diamond] criteriaOrder
        if (diamondOrder != null) {
            criteria.setDiamondOrder(diamondOrder);
        }
        //登录时间
        if (loginDate != null) {
            criteria.setLoginDate(loginDateValid.getDate());
        }
        if (startLoginDate != null) {
            criteria.setStartLoginDate(startLoginDateValid.getDate());
        }
        if (endLoginDate != null) {
            criteria.setEndLoginDate(endLoginDateValid.getDate());
        }
        //table [user][column = login_date] criteriaOrder
        if (loginDateOrder != null) {
            criteria.setLoginDateOrder(loginDateOrder);
        }
        if (strId != null) {
            criteria.setStrId(strId);
        }
        //table [user][column = str_id] criteriaOrder
        if (strIdOrder != null) {
            criteria.setStrIdOrder(strIdOrder);
        }
        if (gatewayToken != null) {
            criteria.setGatewayToken(Long.valueOf(gatewayToken));
        }
        return criteria;
    }

    @Override
    protected void rangeStr(StringBuilder sb) {
        if (startLoginDate != null) {
            sb.append("startLoginDate=").append(startLoginDate).append(",");
        }
        if (endLoginDate != null) {
            sb.append("endLoginDate=").append(endLoginDate).append(",");
        }
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("UserCriteriaStr{");
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

    @Override
    protected void afterStr(StringBuilder sb) {
        if (nickOrder != null) {
            sb.append("nickOrder=").append(nickOrder).append(",");
        }
        if (diamondOrder != null) {
            sb.append("diamondOrder=").append(diamondOrder).append(",");
        }
        if (loginDateOrder != null) {
            sb.append("loginDateOrder=").append(loginDateOrder).append(",");
        }
        if (strIdOrder != null) {
            sb.append("strIdOrder=").append(strIdOrder).append(",");
        }
        super.afterStr(sb);
    }

    /**
     * get (主键)
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * set (主键)
     *
     * @return
     */
    public UserCriteriaStr setId(String id) {
        if (id != null && id.trim().length() == 0) {
            return this;
        }
        this.id = id;
        return this;
    }

    /**
     * get 乐观锁，版本控制
     *
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     * set 乐观锁，版本控制
     *
     * @return
     */
    public UserCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
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
    public UserCriteriaStr setNick(String nick) {
        if (nick != null && nick.trim().length() == 0) {
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
    public UserCriteriaStr setNickOrder(String nickOrder) {
        if (nickOrder != null && nickOrder.trim().length() == 0) {
            this.nickOrder = null;
            return this;
        }
        this.nickOrder = nickOrder;
        return this;
    }

    /**
     * get 钻石
     *
     * @return
     */
    public String getDiamond() {
        return diamond;
    }

    /**
     * set 钻石
     *
     * @return
     */
    public UserCriteriaStr setDiamond(String diamond) {
        if (diamond != null && diamond.trim().length() == 0) {
            return this;
        }
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
    public UserCriteriaStr setDiamondOrder(String diamondOrder) {
        if (diamondOrder != null && diamondOrder.trim().length() == 0) {
            this.diamondOrder = null;
            return this;
        }
        this.diamondOrder = diamondOrder;
        return this;
    }

    /**
     * get 登录时间
     *
     * @return
     */
    public String getLoginDate() {
        return loginDate;
    }

    /**
     * set 登录时间
     *
     * @return
     */
    public UserCriteriaStr setLoginDate(String loginDate) {
        if (loginDate != null && loginDate.trim().length() == 0) {
            return this;
        }
        this.loginDate = loginDate;
        return this;
    }

    public String getLoginDatePattern() {
        return loginDatePattern;
    }

    public UserCriteriaStr setLoginDatePattern(String loginDatePattern) {
        if (loginDatePattern != null && loginDatePattern.trim().length() == 0) {
            return this;
        }
        this.loginDatePattern = loginDatePattern;
        this.startLoginDateValid.setPattern(loginDatePattern);
        this.endLoginDateValid.setPattern(loginDatePattern);
        return this;
    }

    /**
     * get start 登录时间
     *
     * @return
     */
    public String getStartLoginDate() {
        return startLoginDate;
    }

    /**
     * set start 登录时间
     *
     * @return
     */
    public UserCriteriaStr setStartLoginDate(String startLoginDate) {
        if (startLoginDate != null && startLoginDate.trim().length() == 0) {
            return this;
        }
        this.startLoginDate = startLoginDate;
        this.startLoginDateValid.setDateStr(startLoginDate);
        return this;
    }

    /**
     * get end 登录时间
     *
     * @return
     */
    public String getEndLoginDate() {
        return endLoginDate;
    }

    /**
     * set end 登录时间
     *
     * @return
     */
    public UserCriteriaStr setEndLoginDate(String endLoginDate) {
        if (endLoginDate != null && endLoginDate.trim().length() == 0) {
            return this;
        }
        this.endLoginDate = endLoginDate;
        this.endLoginDateValid.setDateStr(endLoginDate);
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
    public UserCriteriaStr setLoginDateOrder(String loginDateOrder) {
        if (loginDateOrder != null && loginDateOrder.trim().length() == 0) {
            this.loginDateOrder = null;
            return this;
        }
        this.loginDateOrder = loginDateOrder;
        return this;
    }

    public String getStrId() {
        return strId;
    }


    public UserCriteriaStr setStrId(String strId) {
        if (strId != null && strId.trim().length() == 0) {
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
    public UserCriteriaStr setStrIdOrder(String strIdOrder) {
        if (strIdOrder != null && strIdOrder.trim().length() == 0) {
            this.strIdOrder = null;
            return this;
        }
        this.strIdOrder = strIdOrder;
        return this;
    }

    public String getGatewayToken() {
        return gatewayToken;
    }


    public UserCriteriaStr setGatewayToken(String gatewayToken) {
        if (gatewayToken != null && gatewayToken.trim().length() == 0) {
            return this;
        }
        this.gatewayToken = gatewayToken;
        return this;
    }

}