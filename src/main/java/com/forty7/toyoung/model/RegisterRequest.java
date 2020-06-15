package com.forty7.toyoung.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 注册实体类
 */

@Data
public class RegisterRequest {

    @NotNull(message="手机号必须填")
    @Pattern(regexp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$", message="账号请输入11位手机号") // 手机号
    private String mobile;

    @NotNull(message="昵称必须填")
    @Size(min=1, max=20, message="昵称1~20个字")
    private String nickname;

    @NotNull(message="密码必须填")
    @Size(min=6, max=16, message="密码6~16位")
    private String password;

}
