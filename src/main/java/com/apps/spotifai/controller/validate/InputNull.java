package com.apps.spotifai.controller.validate;

import java.time.LocalDate;

public class InputNull {
    public boolean validate(String username, String password) {
        if (username.isEmpty() || password.isEmpty())
            return true;
        else
            return false;
    }

    public boolean validate(String username, String name, LocalDate birthdate, String password, String repassword) {
        if (username.isEmpty() || name.isEmpty() || birthdate == null || password.isEmpty() || repassword.isEmpty())
            return true;
        else
            return false;
    }
}
