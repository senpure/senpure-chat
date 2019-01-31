package com.senpure.chat.data.result;

import com.senpure.base.result.ActionResult;
import com.senpure.base.result.Result;
import com.senpure.chat.data.model.User;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Locale;

/**
 * UserEntity
 *
 * @author senpure-generator
 * @version 2019-1-31 10:13:52
 */
public class UserRecordResult extends ActionResult {
    private static final long serialVersionUID = 1699538358L;

    @ApiModelProperty(position = 3)
    private User user;

    public static UserRecordResult success() {
        return new UserRecordResult(Result.SUCCESS);
    }

    public static UserRecordResult dim() {
        return new UserRecordResult(Result.ERROR_DIM);
    }

    public static UserRecordResult failure() {
        return new UserRecordResult(Result.FAILURE);
    }

    public static UserRecordResult notExist() {
        return new UserRecordResult(Result.TARGET_NOT_EXIST);
    }

    public static UserRecordResult result(int code) {
        return new UserRecordResult(code);
    }

    public UserRecordResult(int code) {
        super(code);
    }

    public User getUser() {
        return user;
    }

    public UserRecordResult setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public UserRecordResult setClientFormat(boolean clientFormat) {
        super.setClientFormat(clientFormat);
        return this;
    }

    @Override
    public UserRecordResult setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    @Override
    public UserRecordResult setMessageArgs(List<String> messageArgs) {
        super.setMessageArgs(messageArgs);
        return this;
    }

    @Override
    public UserRecordResult wrapMessage(Locale locale) {
        super.wrapMessage(locale);
        return this;
    }

    @Override
    public UserRecordResult wrapMessage(Locale locale, Object... args) {
        super.wrapMessage(locale, args);
        return this;
    }
}