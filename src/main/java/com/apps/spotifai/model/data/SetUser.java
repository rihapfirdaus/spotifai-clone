package com.apps.spotifai.model.data;

import com.apps.spotifai.model.DataBase.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SetUser {
    public String setData(User user) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
            ResponseEntity<Void> responseEntity = restTemplate.postForEntity("http://localhost:8081/api/users/create", requestEntity, Void.class);

            HttpStatusCode statusCode = responseEntity.getStatusCode();

            if (statusCode == HttpStatus.CREATED) {
                return "success";
            } else {
                return "failed";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "failed";
        }
    }
}
