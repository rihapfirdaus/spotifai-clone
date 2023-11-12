package com.apps.spotifai.controller.scene;

import com.apps.spotifai.controller.model.UserController;
import com.apps.spotifai.controller.validate.InputNull;
import com.apps.spotifai.controller.validate.PasswordAuthentication;
import com.apps.spotifai.model.DataBase.User;
import com.apps.spotifai.model.data.GetUser;
import com.apps.spotifai.view.SwitchScene.SwitchToDashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private GetUser getUser = new GetUser();
    private UserController userController = new UserController();
    private SwitchToDashboard switchToDashboard = new SwitchToDashboard();
    private InputNull inputNull = new InputNull();
    private PasswordAuthentication passwordAuthentication = new PasswordAuthentication();

    private String username;
    private String password;
    private User user;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text LabelMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelMessage.setVisible(false);
    }

    private void getData(){
        this.username = usernameField.getText();
        this.password = passwordField.getText();
    }

    private void setLabelMessage(boolean condition, String message){
        LabelMessage.setText(message);
        if(condition){
            LabelMessage.setFill(Color.GREEN);
        } else {
            LabelMessage.setFill(Color.RED);
        }
        LabelMessage.setVisible(true);
    }
    public void handleButtonClick(ActionEvent event) throws IOException {
        this.getData();
        if (inputNull.validate(this.username, this.password)){
            setLabelMessage(false, "please fill out all forms ");
            return;
        }
        User user = getUser.getData(username);
        if (user == null){
            setLabelMessage(false, "username not found");
            return;
        }
        if (!(passwordAuthentication.validate(user, username, password))){
            setLabelMessage(false, "incorrect username or password");
            return;
        }
        setLabelMessage(true, "login succes, please wait..");
        switchToDashboard.switchScene(event);
    }
}
