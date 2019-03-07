package com.senpure.chat.game.service;

import com.senpure.chat.data.result.UserRecordResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * PlayerServerRemote
 *
 * @author senpure
 * @time 2019-03-06 15:08:25
 */

@FeignClient(value = "chat-data")
public interface PlayerServerRemote {

    @GetMapping("user/{id}")
    public UserRecordResult readUser(@PathVariable("id") long id);

}
