package com.forty7.toyoung.repository;

import com.forty7.toyoung.model.User;

public interface UserMapper {
    // 对应xml映射文件元素的ID
    User selectByPrimaryKey(long id);
}
