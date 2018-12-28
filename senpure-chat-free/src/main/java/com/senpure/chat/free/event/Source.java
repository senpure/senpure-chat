package com.senpure.chat.free.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Source
 *
 * @author senpure
 * @time 2018-12-14 17:19:47
 */
public interface Source {

    String EVENT_LOGIN_OUTPUT = "loginoutput";
    @Output(EVENT_LOGIN_OUTPUT)
    MessageChannel sendLogin();



}
