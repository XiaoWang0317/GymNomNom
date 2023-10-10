package com.gymnomnom.gymnomnom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class GymnomnomApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymnomnomApplication.class, args);
    }

}

