package com.senpure.chat.event;

import com.senpure.base.AppEvn;
import com.senpure.base.util.DateFormatUtil;
import com.senpure.chat.DataBoot;
import com.senpure.chat.protocol.bean.User;
import com.senpure.chat.protocol.event.EventSender;
import com.senpure.chat.protocol.event.UserLoginEvent;
import com.senpure.io.event.EventHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={DataBoot.class})
public class EventSenderTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EventSender eventSender;
    @BeforeClass
    public static void before() {

        AppEvn.markStartClass(DataBoot.class);
    }

    @Test
    public void test() throws InterruptedException {

        EventHelper.setService(Executors.newSingleThreadScheduledExecutor());

        int count=0;
        while (true) {
            UserLoginEvent event = new UserLoginEvent();
            event.setTime(System.currentTimeMillis());
            User user = new User();
            user.setDiamond(500);
            user.setNick("nick" + DateFormatUtil.format(new Date()));
            user.setUserId(count);
            event.setUser(user);
            eventSender.send(event);
            count++;
            Thread.sleep(5);
        }

    }


}
