package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.pojo.User;
import com.gymnomnom.gymnomnom.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;
    @PostMapping("/signup")
    public Result signup(@RequestBody User user) {
        log.info("Add a user to database");
        int return_type = accountService.add(user);
        if (return_type == 0) {
            return Result.error("Unreasonable age number");
        }
        return Result.success();
    }

}
