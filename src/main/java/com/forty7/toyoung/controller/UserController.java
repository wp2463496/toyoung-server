package com.forty7.toyoung.controller;

import com.forty7.toyoung.model.User;
import com.forty7.toyoung.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
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



    // 注入RedisTemplat
    @Resource
    private RedisTemplate<String, Object> redis;

    // 读取用户信息，测试缓存使用：除了首次读取，接下来都应该从缓存中读取
    @ApiOperation(value="读取用户信息，测试缓存使用")
    @RequestMapping(value="/getUserCache/{id}", method=RequestMethod.GET, produces="application/json")
    public User getUserCache(@PathVariable long id) throws Exception {
        User user = this.userService.getUserById(id);
        return user;
    }

    // 修改用户信息，测试删除缓存
    @ApiOperation(value="修改用户信息，测试删除缓存")
    @RequestMapping(value = "/getUserCache/{id}/change-nick", method = RequestMethod.POST, produces="application/json")
    public User changeNickname(@PathVariable long id) throws Exception{
        String nick = "abc-" + Math.random();
        User user = this.userService.updateUserNicknameCache(id, nick);
        return user;
    }

    // 使用RedisTemplate访问redis服务器
    @ApiOperation(value="使用RedisTemplate访问redis服务器")
    @RequestMapping(value="/redis", method=RequestMethod.GET, produces="application/json")
    public String redis() throws Exception {
        // 设置键"project-name"，值"qikegu-springboot-redis-demo"
        redis.opsForValue().set("project-name", "qikegu-springboot-redis-demo");
        String value = (String) redis.opsForValue().get("project-name");
        return value;
    }


}