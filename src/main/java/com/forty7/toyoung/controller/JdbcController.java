package com.forty7.toyoung.controller;

import com.forty7.toyoung.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库控制类
 */

@Api(description = "用户API接口")
@RestController
@EnableAutoConfiguration
@RequestMapping("/jdbc")
public class JdbcController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @ApiOperation(value="用户列表查询", notes="用户列表查询接口", produces="application/json")
    @RequestMapping(value="/userList", method = RequestMethod.GET)
    public List<User> userList() {
        String sql = "SELECT * FROM user";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            User user = null;
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setSex(rs.getString("sex"));
                user.setAge(rs.getInt("age"));
                return user;
            }
        });
        return userList;
    }

}