package com.apps.spotifai.controller.scene;

import com.apps.spotifai.controller.validate.InputNull;
import com.apps.spotifai.controller.validate.InputPassword;
import com.apps.spotifai.model.DataBase.User;
import com.apps.spotifai.model.data.GetUser;
import com.apps.spotifai.model.data.SetUser;
import com.apps.spotifai.view.SwitchScene.SwitchToLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    private SetUser setUser = new SetUser();
    private GetUser getUser = new GetUser();
    private SwitchToLogin switchToLogin = new SwitchToLogin();
    private InputNull inputNull = new InputNull();
    private InputPassword inputPassword = new InputPassword();

    private String username;
    private String name;
    private LocalDate birthdate;
    private String password;
    private String repassword;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker birthdateField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repasswordField;

    @FXML
    private Text LabelMessage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelMessage.setVisible(false);
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
    private void getData(){
        this.username = usernameField.getText();
        this.name = nameField.getText();
        this.birthdate = birthdateField.getValue();
        this.password = passwordField.getText();
        this.repassword = repasswordField.getText();
    }
    public void handleButtonClick(ActionEvent event) throws IOException {
        this.getData();
        if(inputNull.validate(this.username, this.name, this.birthdate, this.password, this.repassword)){
            setLabelMessage(false, "please fill out all forms ");
            return;
        }
        if(!(inputPassword.validate(this.password, this.repassword))){
            setLabelMessage(false, "password not same");
            return;
        }
        User user = getUser.getData(this.username);
        if(!(user == null)){
            setLabelMessage(false, "username already in use");
            return;
        }
        user = new User(this.username, this.name, this.birthdate, this.password);
        if(setUser.setData(user).equals("failed")){
            setLabelMessage(false, "signup failed, please try again");
            return;
        }
        setLabelMessage(true, "signup succes, please login to your account");
        switchToLogin.switchScene(event);
    }
}
