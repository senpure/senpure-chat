package com.senpure.chat;

import com.senpure.base.AppEvn;
import com.senpure.chat.client.ui.view.ClientView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * UiBoot
 *
 * @author senpure
 * @time 2018-12-26 15:55:42
 */
@SpringBootApplication
public class UiBoot extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        AppEvn.installAnsiConsole();
        AppEvn.markClassRootPath();

        launch(UiBoot.class, ClientView.class, args);

    }
}
