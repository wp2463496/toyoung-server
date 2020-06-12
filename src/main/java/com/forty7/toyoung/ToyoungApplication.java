package com.forty7.toyoung;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//指定要扫描的mybatis映射类的路径
@MapperScan("com.forty7.toyoung.repository")
public class ToyoungApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToyoungApplication.class, args);
    }

}
