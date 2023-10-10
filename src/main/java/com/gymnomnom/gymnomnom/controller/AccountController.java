package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.pojo.User;
import com.gymnomnom.gymnomnom.service.AccountService;
import com.gymnomnom.gymnomnom.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Sign up a account
     * @param user
     * @return Result
     */
    @PostMapping("/signup")
    public Result signup(@RequestBody User user) {
        log.info("Add a user to database");
        int return_type = 0;
        try {
            return_type = accountService.add(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    /**
     * Delete an account
     * @param id
     * @return Result
     */
    @DeleteMapping("/{id}/delete")
    public Result delete(@PathVariable Integer id) {
        log.info("Delete an account based on the id: {}", id);
        accountService.delete(id);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("Log in: {}", user);
        User u = accountService.login(user);
        //success
        if (u != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("name", u.getName());
            claims.put("password", u.getPassword());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        //fail
        return Result.error("Invalid name or password");
    }
}
