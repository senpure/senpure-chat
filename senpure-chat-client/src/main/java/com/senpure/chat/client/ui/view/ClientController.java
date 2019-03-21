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
import com.senpure.io.OffLineHandler;
import com.senpure.io.OffLineListener;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import io.netty.channel.Channel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
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

    private Channel channel;
    private int position = 0;
    private long playerId = 0;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private boolean reConnect = true;

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

    @PostConstruct
    public void connect() {
        try {
            Channel temp = clientServer.connect();
            if (temp != null && temp != channel) {
                Platform.runLater(() -> GUIState.getStage().setTitle("|chat-客户端|-连接成功"));
                reConnect = true;
                message(temp.toString() + "建立连接成功");
                OffLineHandler.regChannelOffLineListener(temp, new OffLineListener() {
                    @Override
                    public void executeOffLine(Channel channel) {
                        Platform.runLater(() -> GUIState.getStage().setTitle("|chat-客户端|-连接断开"));
                        message(channel.toString() + "断开连接");
                        if (reConnect) {
                            message(channel.toString() + "开始重连。。。。");
                            connect();
                        }
                    }

                    @Override
                    public String getOffLineListenerName() {
                        return "chat-掉线处理";
                    }
                });
                channel = temp;
            } else {
                if (channel == null || !channel.isActive()) {
                    Platform.runLater(() -> GUIState.getStage().setTitle("|chat-客户端|-没有连接"));
                    message("建立连接失败");
                }
            }

        } catch (Exception e) {
            logger.error("连接出错", e);
        }
    }

    public void close() {
        reConnect = false;
        if (channel != null) {
            channel.close();
        }
    }

    public void loginSuccess(SCUserLoginMessage message) {

        playerId = message.getUser().getUserId();
        Platform.runLater(() -> {
            User user = message.getUser();
            textFieldNick.setText(user.getNick());
            textAreaCore.appendText(user.getNick() + "[" + user.getUserId() + "]登录成功!\n");
            GUIState.getStage().setTitle("|chat-客户端|[" + user.getNick() + "]");
        });
    }

    public void exitRoom(SCExitGameChatMessage message) {
        if (message.getUser().getUserId() == playerId) {
            playerId = 0;
            Platform.runLater(() -> GUIState.getStage().setTitle("|chat-客户端|"));
        }

        message(message.getUser().getNick() + "退出game房间[" + message.getRoomId() + "]");

    }

    public void exitRoom(SCExitFreeChatMessage message) {
        if (message.getUser().getUserId() == playerId) {
            playerId = 0;
            Platform.runLater(() -> GUIState.getStage().setTitle("|chat-客户端|"));
        }
        message(message.getUser().getNick() + "退出free房间[" + message.getRoomId() + "]");
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
            message(user.getNick() + "[" + user.getUserId() + "]进入game房间[" + message.getRoomId() + "]");
            // textAreaCore.appendText(user.getNick() + "[" + user.getUserId() + "]进入game房间!\n");
            if (playerId == user.getUserId()) {
                position = 1;
                GUIState.getStage().setTitle("|chat-客户端|[" + user.getNick() + "]game[" + message.getRoomId() + "]");
            }

        });
    }


    public void loginRoom(SCEntryFreeChatMessage message) {
        Platform.runLater(() -> {
            User user = message.getUser();
            //textFieldNick.setText(user.getNick());
            message(user.getNick() + "[" + user.getUserId() + "]进入free房间[" + message.getRoomId() + "]");
            if (user.getUserId() == playerId) {
                position = 2;
                GUIState.getStage().setTitle("|chat-客户端|[" + user.getNick() + "]free[" + message.getRoomId() + "]");
            }

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

    public void clearMessage() {
        textAreaCore.clear();
    }

    public void keyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && event.isControlDown()) {
            sendChatMessage();
        }
    }

    public void sendChatMessage() {
        if (position == 1) {
            sendGameChatMessage();
        } else if (position == 2) {
            sendFreeChatMessage();
        } else {
            message("您还没有进入房间");
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
