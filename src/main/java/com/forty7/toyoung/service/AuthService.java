package com.forty7.toyoung.service;

import com.forty7.toyoung.model.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
