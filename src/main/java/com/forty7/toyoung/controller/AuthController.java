package com.forty7.toyoung.controller;

import com.forty7.toyoung.model.RegisterRequest;
import com.forty7.toyoung.model.Result;
import com.forty7.toyoung.util.MiscUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户
 */

@Api(description = "用户")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @ApiOperation(value="注册", notes="注册接口")
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<Result> register(@Valid @RequestBody RegisterRequest register, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Result res1 = MiscUtil.getValidateError(bindingResult);
            return new ResponseEntity<Result>(res1, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Result res = new Result(200, "ok");
        res.setData(true);
        return ResponseEntity.ok(res);
    }


}