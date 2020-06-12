package com.forty7.toyoung.controller;

import com.forty7.toyoung.model.TestBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@Api(description = "测试API接口")
@RestController
@EnableAutoConfiguration
@RequestMapping("/toyoung")
public class TestContreller {

    @ApiOperation(value="测试查询Get", notes="测试查询接口", produces="application/json")
    @ApiImplicitParam(name = "text", value = "内容（必填）", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value="/testGet", method = RequestMethod.GET)
    private String testGet(@RequestParam(name = "text", required = true) String text){
        return "get返回" + text + "测试";
    }

    @ApiOperation(value="测试查询Post", notes="测试查询接口", produces="application/json")
    @RequestMapping(value="/testPost", method = RequestMethod.POST)
    @ApiParam()
    private String testPost(@RequestBody @ApiParam(name="提交参数",value="text",required = false) TestBean test){
        return "post返回" + test.getText() + "测试";
    }

}
