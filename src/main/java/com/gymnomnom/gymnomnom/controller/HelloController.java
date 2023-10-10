package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.pojo.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/hello/{num}")
    public Result hello(@PathVariable Integer num, @RequestBody User user) {
        System.out.println("hello " + num);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("A", Arrays.asList(1,2,4));
        map.put("B", Arrays.asList(6,7,8));
        return Result.success(user);
    }

}
