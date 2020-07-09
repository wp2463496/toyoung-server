package com.forty7.toyoung.impl;

import com.forty7.toyoung.model.User;
import com.forty7.toyoung.repository.UserMapper;
import com.forty7.toyoung.service.UserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> listUser(int page, int pageSize) {
        List<User> result = null;
        try {
            // 调用pagehelper分页，采用starPage方式。starPage应放在Mapper查询函数之前
            PageHelper.startPage(page, pageSize); //每页的大小为pageSize，查询第page页的结果
            PageHelper.orderBy("id ASC "); //进行分页结果的排序
            result = userMapper.selectUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private User user = new User(1l, "abc1", "13512345678", "123456", "role-user");
    @Cacheable(value = "user", key= "#id")
    public User getUserByIdCache(long id) {
        log.info("加载用户信息");
        return user;
    }

    @CacheEvict(value = "user", key= "#id")
    public User updateUserNicknameCache(long id, String nickname) {
        user.setNickname(nickname);
        return user;
    }

}