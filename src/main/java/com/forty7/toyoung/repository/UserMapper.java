package com.forty7.toyoung.repository;

import com.forty7.toyoung.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    //用户查询
    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //列出用户，对应xml映射文件元素的ID
    List<User> selectUser();

}