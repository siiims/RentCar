package com.sims.rentcar.auth;

import com.sims.rentcar.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountCredentials {
    @Autowired
    UserRepository userRepository;

    private String username;
    private String password;
    private Long userId;


    public AccountCredentials() {
        this.username = null;
        this.password = null;
    }

    public AccountCredentials(String username, Long userId) {
        this.username = username;
        this.userId = userId;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
