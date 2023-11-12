package com.apps.spotifai.model.DataBase;

import java.time.LocalDate;

public class User {
    private Long id;
    private String username;
    private String name;
    private LocalDate birthdate;
    private String password;

    public User(){}

    public User(String username, String name, LocalDate birthdate, String password) {
        this.username = username;
        this.name = name;
        this.birthdate = birthdate;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", password='" + password + '\'' +
                '}';
    }
}