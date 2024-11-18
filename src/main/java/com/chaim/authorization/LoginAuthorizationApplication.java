package com.chaim.authorization;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chaim.authorization.mapper")
public class LoginAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginAuthorizationApplication.class, args);
    }

}
