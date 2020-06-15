package com.forty7.toyoung.model;

import lombok.Data;

/**
 * 公共请求返回对象
 * @param <T>
 */
@Data
public class Result<T> {
    private int status = -1;
    private String message = "";
    private T Data;

    public Result(){}

    public Result(int status, String message){
        this.status = status;
        this.message = message;
    }

}
