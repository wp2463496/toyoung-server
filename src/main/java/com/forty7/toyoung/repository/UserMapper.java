package com.forty7.toyoung.repository;

import com.forty7.toyoung.model.User;

import java.util.List;

public interface UserMapper {

    // 对应xml映射文件元素的ID
    User selectByPrimaryKey(long id);

    // 列出用户，对应xml映射文件元素的ID
    List<User> selectUser();

}
