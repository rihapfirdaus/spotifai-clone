package com.apps.spotifai.controller.validate;

import com.apps.spotifai.model.DataBase.User;

public class PasswordAuthentication {
    public boolean validate(User user, String username, String password){
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())){
            return true;
        } else {
            return false;
        }
    }
}
