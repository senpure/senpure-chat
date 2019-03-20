package com.senpure.chat.data.result;

import com.senpure.base.result.ActionResult;
import com.senpure.base.result.Result;
import com.senpure.chat.data.model.User;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Locale;

/**
 * User
 *
 * @author senpure-generator
 * @version 2019-3-20 14:12:43
 */
public class UserPageResult extends ActionResult {
    private static final long serialVersionUID = 274272385L;

    public static final String RECORDS_NAME = "users";

    @ApiModelProperty(position = 3, value = "结果集总数", example = "1086")
    private int total;
    @ApiModelProperty(position = 4, value = "一页数据")
    private List<User> users ;

    public static UserPageResult success() {
        return new UserPageResult(Result.SUCCESS);
    }

    public static UserPageResult dim() {
        return new UserPageResult(Result.ERROR_DIM);
    }

    public static UserPageResult failure() {
        return new UserPageResult(Result.FAILURE);
    }

    public static UserPageResult notExist() {
        return new UserPageResult(Result.TARGET_NOT_EXIST);
    }

    public static UserPageResult result(int code) {
        return new UserPageResult(code);
    }

    public UserPageResult() {
    }

    public UserPageResult(int code) {
        super(code);
    }

    public int getTotal() {
        return total;
    }

    public UserPageResult setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public UserPageResult setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    @Override
    public UserPageResult setClientFormat(boolean clientFormat) {
        super.setClientFormat(clientFormat);
        return this;
    }

    @Override
    public UserPageResult setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    @Override
    public UserPageResult setMessageArgs(List<String> messageArgs) {
        super.setMessageArgs(messageArgs);
        return this;
    }

    @Override
    public UserPageResult wrapMessage(Locale locale) {
        super.wrapMessage(locale);
        return this;
    }

    @Override
    public UserPageResult wrapMessage(Locale locale, Object... args) {
        super.wrapMessage(locale, args);
        return this;
    }
}