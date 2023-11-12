package com.apps.spotifai.model.repository;

import com.apps.spotifai.model.DataBase.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int create(User user) {
        return jdbcTemplate.update("INSERT INTO users (username, name, birthdate, password) VALUES(?,?,?,?)",
                new Object[]{user.getUsername(), user.getName(), user.getBirthdate(), user.getPassword()});
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("UPDATE users SET username=?, name=?, birthdate=?, password=? WHERE id=?",
                new Object[]{user.getUsername(), user.getName(), user.getBirthdate(), user.getPassword(), user.getId()});
    }

    @Override
    public User findById(Long id) {
        try{
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
            return user;
        } catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public User findByUsername(String username){
        try{
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?",
                    BeanPropertyRowMapper.newInstance(User.class), username);
            return user;
        } catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM songs WHERE id =?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from users");
    }
}
