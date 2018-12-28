package com.senpure.chat.game.configuration;

import com.senpure.io.event.EventHelper;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;

/**
 * GameConfigration
 *
 * @author senpure
 * @time 2018-12-17 18:02:46
 */
@Configuration
public class GameConfiguration {


    @PostConstruct
    public void init() {
        EventHelper.setService(Executors.newSingleThreadScheduledExecutor());
    }
}
