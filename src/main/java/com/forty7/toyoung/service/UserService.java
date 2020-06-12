package com.forty7.toyoung.service;

import com.forty7.toyoung.model.User;

import java.util.List;

public interface UserService {

    public User getUserById(long userId);

    public List<User> listUser(int page, int pageSize);

}
