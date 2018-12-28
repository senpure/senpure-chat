package com.senpure.chat.client.ui.view;

import com.senpure.chat.data.protocol.message.CSUserLoginMessage;
import com.senpure.chat.data.protocol.message.SCUserLoginMessage;
import com.senpure.chat.protocol.bean.User;
import com.senpure.io.ClientServer;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * ClientController
 *
 * @author senpure
 * @time 2018-12-26 15:29:55
 */
@FXMLController
public class ClientController implements Initializable {
    @FXML
    TextField textFieldStrId;
    @FXML
    TextField textFieldNick;
    @FXML
    TextArea textAreaCore;
    @Autowired
    private ClientServer clientServer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login() {
        String strId = textFieldStrId.getText();
        if (strId == null || strId.trim().length() == 0) {
            strId = UUID.randomUUID().toString().replace("-", "");
            textFieldStrId.setText(strId);
        }
        CSUserLoginMessage loginMessage = new CSUserLoginMessage();
        loginMessage.setId(strId);
        loginMessage.setNick(textFieldNick.getText());
        clientServer.getChannel().writeAndFlush(loginMessage);
    }

    public void loginSuccess(SCUserLoginMessage message) {

        Platform.runLater(() -> {
            User user = message.getUser();
            textFieldNick.setText(user.getNick());
            textAreaCore.appendText(user.getNick() + "[" + user.getUserId() + "]登录成功!\n");
        });
    }
}
