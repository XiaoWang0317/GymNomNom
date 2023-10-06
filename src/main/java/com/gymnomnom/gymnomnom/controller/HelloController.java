package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Result hello() {
        System.out.println("hello");
        return Result.success("hello");
    }
}
