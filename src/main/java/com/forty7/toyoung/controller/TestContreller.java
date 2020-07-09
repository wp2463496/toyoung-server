package com.forty7.toyoung.controller;

import com.forty7.toyoung.model.Result;
import com.forty7.toyoung.model.TestBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "测试API接口")
@RestController
@EnableAutoConfiguration
@RequestMapping("/toyoung")
public class TestContreller {

    @ApiOperation(value="测试查询Get", notes="测试查询接口")
    @ApiImplicitParam(name = "text", value = "内容（必填）", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value="/testGet", method = RequestMethod.GET, produces="application/json")
    private String testGet(@RequestParam(name = "text", required = true) String text){
        return "get返回" + text + "测试";
    }

    //返回值乱码或者问号的问题 produces="application/json" 需要写在RequestMapping上
    @ApiOperation(value="测试查询Post", notes="测试查询接口")
    @RequestMapping(value="/testPost", method = RequestMethod.POST, produces="application/json")
    @ApiParam()
    private String testPost(@RequestBody @ApiParam(name="提交参数",value="text",required = false) TestBean test){
        return "post返回" + test.getText() + "测试";
    }

    @ApiOperation(value="封装返回值结构", notes="封装返回值结构接口")
    @RequestMapping(value="/resultTest", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Result> resultTest(@RequestParam(value="bad", required=false, defaultValue="false") boolean bad) {
        //结果封装类对象
        Result res = new Result(200, "ok");
        if(bad) {
            res.setStatus(400);
            res.setMessage("Bad request");
            // ResponseEntity是响应实体泛型，通过它可以设置http响应的状态值，此处返回400
            return new ResponseEntity<Result>(res, HttpStatus.BAD_REQUEST);
        }
        //把结果数据放进封装类
        Map<String, Object> data = new HashMap<>();
        data.put("words", "Hello world!");
        //把结果数据放进封装数组
//        List<Object> list = new ArrayList<>();
//        list.add(data);
//        list.add(data);
//        list.add(data);
        res.setData(data);
        // ResponseEntity是响应实体泛型，通过它可以设置http响应的状态值，此处返回200
        return ResponseEntity.ok(res);
    }


    @ApiOperation(value="不受限访问", notes="不受限访问接口")
    @RequestMapping(value="/test1", method=RequestMethod.GET)
    public String test1() {
        return "test1!";
    }

    @ApiOperation(value="需要登录访问", notes="需要登录访问接口")
    @RequestMapping(value="/test2", method=RequestMethod.GET)
    public String test2() {
        return "test2!";
    }



}
