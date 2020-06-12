package com.forty7.toyoung.controller;

import com.forty7.toyoung.model.User;
import com.forty7.toyoung.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(description = "用户API接口")
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value="用户查询", notes="用户查询接口", produces="application/json")
    @RequestMapping(value="{id}", method= RequestMethod.GET)
    public User getUser(@PathVariable long id) throws Exception {
        User user = this.userService.getUserById(id);
        return user;
    }

    @ApiOperation(value="用户列表查询", notes="用户列表分页查询", produces="application/json")
    @RequestMapping(value="", method = RequestMethod.GET)
    public PageInfo<User> listUser(@RequestParam(value="page", required=false, defaultValue="1") int page, @RequestParam(value="page-size", required=false, defaultValue="5") int pageSize){
        List<User> result = userService.listUser(page, pageSize);
        PageInfo<User> pi = new PageInfo<User>(result);
        return pi;
    }


}