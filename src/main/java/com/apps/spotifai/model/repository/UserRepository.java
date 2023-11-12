package com.apps.spotifai.model.repository;

import com.apps.spotifai.model.DataBase.User;

public interface UserRepository {
    int create(User user);
    int update(User user);
    User findById(Long id);
    User findByUsername(String username);
    int deleteById(Long id);
    int deleteAll();
}