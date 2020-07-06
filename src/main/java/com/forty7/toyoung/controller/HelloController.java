package com.forty7.toyoung.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    // 当前环境配置名称
    @Value("${profile.name}") //读取当前环境配置名称
    private String profileName;


    @RequestMapping(value="/hello1", method= RequestMethod.GET)
    public String hello1() {

        return "Hello1!";
    }

    @RequestMapping(value="/hello2", method=RequestMethod.GET)
    public String hello2() {

        return "Hello2!";
    }

    @RequestMapping(value="/hello3", method=RequestMethod.GET)
    public String hello3() {

        return "Hello3!";
    }

    @ApiOperation(value="获取当前环境", notes="获取当前环境接口")
    @RequestMapping(value="/profileName", method = RequestMethod.GET, produces="application/json")
    public String getProfileName() {
        return "当前环境：" + profileName;
    }

}
