package com.senpure.chat.protocol.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * SourceDiamondChange
 *
 * @author senpure
 * @time 2018-12-26 11:03:02
 */
public interface SourceDiamondChange {

    String DIAMOND_CHANGE_OUTPUT = "diamond-change-output";

    @Output(DIAMOND_CHANGE_OUTPUT)
    MessageChannel output();
}
