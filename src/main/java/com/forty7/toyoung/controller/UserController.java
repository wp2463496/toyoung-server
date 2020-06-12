package com.forty7.toyoung.controller;

import com.forty7.toyoung.model.User;
import com.forty7.toyoung.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "用户API接口")
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    // 注入mapper类
    @Resource
    private UserService userService;

    @ApiOperation(value="用户查询", notes="用户查询接口", produces="application/json")
    @RequestMapping(value="{id}", method= RequestMethod.GET)
    public User getUser(@PathVariable long id) throws Exception {
        User user = this.userService.getUserById(id);
        return user;
    }

}