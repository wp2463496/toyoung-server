package com.forty7.toyoung.controller;

import com.forty7.toyoung.model.LoginRequest;
import com.forty7.toyoung.model.RegisterRequest;
import com.forty7.toyoung.model.Result;
import com.forty7.toyoung.model.Results;
import com.forty7.toyoung.service.AuthService;
import com.forty7.toyoung.util.MiscUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


    @Autowired
    private AuthService authService;

    /**
     * login
     * @param authRequest
     * @param bindingResult
     * @return ResponseEntity<Result>
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<Results> login(@Valid @RequestBody LoginRequest authRequest, BindingResult bindingResult) throws AuthenticationException {
        if(bindingResult.hasErrors()) {
            Results res = MiscUtil.getValidateErrors(bindingResult);
            return new ResponseEntity<Results>(res, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        final String token = authService.login(authRequest.getAccount(), authRequest.getPassword());
        // Return the token
        Results res = new Results(200, "ok");
        res.putData("token", token);
        return ResponseEntity.ok(res);
    }


    /**
     * refresh
     * @param request
     * @return ResponseEntity<Result>
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Results> refresh(HttpServletRequest request, @RequestParam String token) throws AuthenticationException{
        Results res = new Results(200, "ok");
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            res.setStatus(400);
            res.setMessage("无效token");
            return new ResponseEntity<Results>(res, HttpStatus.BAD_REQUEST);
        }
        res.putData("token", token);
        return ResponseEntity.ok(res);
    }


    /**
     * 异常封装返回示例
     * @param register
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register_eh", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<Result> register(@Valid @RequestBody RegisterRequest register) throws Exception {
        Result res = new Result(200, "ok");
        return ResponseEntity.ok(res);
    }


}