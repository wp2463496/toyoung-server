package com.forty7.toyoung.model;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String name;

    private Integer age;

    private String sex;

    public User(Long id, String name, Integer age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User() {
        super();
    }

}