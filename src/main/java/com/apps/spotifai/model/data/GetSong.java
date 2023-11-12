package com.apps.spotifai.model.data;

import com.apps.spotifai.model.DataBase.Song;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class GetSong {
    public List<Song> getAllData(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/api/songs";

        try {
            ResponseEntity<List<Song>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Song>>() {}
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                List<Song> songs = response.getBody();
                System.out.println("jalan");
                return songs;
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
    public List<Song> getAllDataByTitle(String title){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/api/users?title=" + title;

        try {
            ResponseEntity<List<Song>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Song>>() {}
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                List<Song> songs = response.getBody();
                return songs;
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
