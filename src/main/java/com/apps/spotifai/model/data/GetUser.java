package com.apps.spotifai.model.data;

import com.apps.spotifai.model.DataBase.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GetUser {
    public User getData(String username){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/api/users/find/username/{username}";

        try {
            ResponseEntity<User> response = restTemplate.getForEntity(url, User.class, username);
            if (response.hasBody()) {
                return response.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
