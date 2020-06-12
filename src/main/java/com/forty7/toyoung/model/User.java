package com.forty7.toyoung.model;

import lombok.Data;

/**
 * 用户实体类
 */

@Data
public class User {
    //用户id
    private long id;

    //用户名称
    private String name;

    //用户年龄
    private Integer age;

    //用户性别
    private String sex;

    public User(){}

    public User(long id, String name, Integer age, String sex){
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

}
