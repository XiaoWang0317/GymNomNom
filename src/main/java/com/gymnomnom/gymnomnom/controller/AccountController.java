package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.pojo.User;
import com.gymnomnom.gymnomnom.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//        if (return_type == 0) {
//            return Result.error("Unreasonable age number");
//        }
        return Result.success();
    }
    @DeleteMapping("/{id}/delete")
    public Result delete(@PathVariable Integer id) {
        log.info("Delete an account based on the id: {}", id);
        accountService.delete(id);
        return Result.success();
    }

}
