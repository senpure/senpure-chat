package com.senpure.chat.data.criteria;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ChangeDiamondCriteria
 *
 * @author senpure
 * @time 2018-12-28 17:01:07
 */
public class ChangeDiamondCriteria implements Serializable {
    private static final long serialVersionUID = -4783537207940378489L;
    @NotNull
    private Long id;
    @NotNull
    private String type;
    @NotNull
    private Long diamond;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Long getId() {
        return id;
    }

    public ChangeDiamondCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getDiamond() {
        return diamond;
    }

    public void setDiamond(Long diamond) {
        this.diamond = diamond;
    }
}
