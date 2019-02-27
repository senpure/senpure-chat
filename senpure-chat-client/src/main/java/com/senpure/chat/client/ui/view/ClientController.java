package com.senpure.chat.client.ui.view;

import com.senpure.chat.data.protocol.message.CSUserLoginMessage;
import com.senpure.chat.data.protocol.message.SCUserLoginMessage;
import com.senpure.chat.free.protocol.message.CSCreateFreeChatMessage;
import com.senpure.chat.free.protocol.message.CSFreeChatMessage;
import com.senpure.chat.free.protocol.message.SCEntryFreeChatMessage;
import com.senpure.chat.free.protocol.message.SCExitFreeChatMessage;
import com.senpure.chat.game.protocol.message.CSCreateGameChatMessage;
import com.senpure.chat.game.protocol.message.CSGameChatMessage;
import com.senpure.chat.game.protocol.message.SCEntryGameChatMessage;
import com.senpure.chat.game.protocol.message.SCExitGameChatMessage;
import com.senpure.chat.protocol.bean.User;
import com.senpure.chat.protocol.message.CSJoinRoomMessage;
import com.senpure.io.ClientServer;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
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
    @FXML
    TextArea textAreaSend;
    @FXML
    TextField textRoomId;
    @Autowired
    private ClientServer clientServer;


    @Autowired
    private ClientView clientView;
    private int position = 0;
    private long playerId = 0;

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

        playerId = message.getUser().getUserId();
        Platform.runLater(() -> {
            User user = message.getUser();
            textFieldNick.setText(user.getNick());
            textAreaCore.appendText(user.getNick() + "[" + user.getUserId() + "]登录成功!\n");
            GUIState.getStage().setTitle(user.getNick());
            GUIState.setTitle("|" + user.getNick() + "|");
        });
    }

    public void exitRoom(SCExitGameChatMessage message) {
        if (message.getUser().getUserId() == playerId) {
            playerId = 0;
        }

        message(message.getUser().getNick() + "退出game房间");
    }

    public void exitRoom(SCExitFreeChatMessage message) {
        if (message.getUser().getUserId() == playerId) {
            playerId = 0;
        }

        message(message.getUser().getNick() + "退出free房间");
    }

    public void createGameRoom() {
        CSCreateGameChatMessage message = new CSCreateGameChatMessage();
        clientServer.getChannel().writeAndFlush(message);
    }

    public void createFreeRoom() {
        CSCreateFreeChatMessage message = new CSCreateFreeChatMessage();
        clientServer.getChannel().writeAndFlush(message);
    }

    public void loginRoom(SCEntryGameChatMessage message) {
        Platform.runLater(() -> {
            User user = message.getUser();
            //textFieldNick.setText(user.getNick());
            textAreaCore.appendText(user.getNick() + "[" + user.getUserId() + "]进入game房间!\n");
            position = 1;
        });
    }


    public void loginRoom(SCEntryFreeChatMessage message) {
        Platform.runLater(() -> {
            User user = message.getUser();
            //textFieldNick.setText(user.getNick());
            textAreaCore.appendText(user.getNick() + "[" + user.getUserId() + "]进入free房间!\n");
            position = 2;
        });
    }

    public void joinRoom() {
        CSJoinRoomMessage message = new CSJoinRoomMessage();
        int roomId = Integer.parseInt(textRoomId.getText());
        message.setRoomId(roomId + "");
        clientServer.getChannel().writeAndFlush(message);
    }

    public void message(String message) {
        Platform.runLater(() -> textAreaCore.appendText(message + "\n"));
    }

    public void sendChatMessage() {
        if (position == 1) {
            sendGameChatMessage();
        } else if (position == 2) {
            sendFreeChatMessage();
        }
    }

    public void sendGameChatMessage() {
        String text = textAreaSend.getText();
        textAreaSend.clear();
        CSGameChatMessage message = new CSGameChatMessage();
        message.setContent(text);
        clientServer.getChannel().writeAndFlush(message);
    }

    public void sendFreeChatMessage() {
        String text = textAreaSend.getText();
        textAreaSend.clear();
        CSFreeChatMessage message = new CSFreeChatMessage();
        message.setContent(text);
        clientServer.getChannel().writeAndFlush(message);
    }
}
