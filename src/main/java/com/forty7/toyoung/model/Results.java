package com.forty7.toyoung.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共请求返回对象
 * @param <T>
 */
@Data
public class Results<T> {
    private int status = -1;
    private String message = "待处理";
    private Map<String, Object> data = new HashMap<String, Object>();


    public Results(){}

    public Results(int status, String message){
        this.status = status;
        this.message = message;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void putData(String key, Object value) {
        data.put(key, value);
    }

    public void removeData(String key) {
        data.remove(key);
    }


}
