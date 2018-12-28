package com.senpure.chat.data.criteria;

import java.io.Serializable;

/**
 * ChangeDiamondCriteria
 *
 * @author senpure
 * @time 2018-12-28 17:01:07
 */
public class ChangeDiamondCriteria  implements Serializable {
    private static final long serialVersionUID = -4783537207940378489L;

    private String type;
    private Long userId;
    private Long diamond;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDiamond() {
        return diamond;
    }

    public void setDiamond(Long diamond) {
        this.diamond = diamond;
    }
}
