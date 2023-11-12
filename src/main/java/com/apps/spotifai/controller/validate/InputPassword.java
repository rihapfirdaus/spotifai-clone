package com.apps.spotifai.controller.validate;

public class InputPassword {
    public boolean validate(String password, String repassword){
        if(password.equals(repassword))
            return true;
        else
            return false;
    }
}
