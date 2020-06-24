package com.forty7.toyoung.model;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String name;

    private Integer age;

    private String sex;

    //测试权限增加的

    private String role;

    private String mobile;

    private String password;

    private String nickname;

    public User(Long id, String name, Integer age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User(Long id, String nickname, String mobile, String password, String role) {
        this.id = id;
        this.nickname = nickname;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }

    public User() {
        super();
    }

}