package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.pojo.Body;
import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.service.AccountService;
import com.gymnomnom.gymnomnom.service.BodyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BodyController {

    @Autowired
    private BodyService bodyService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/{id}/inputbody")
    public Result inputBody(@PathVariable Integer id, @RequestBody Body body) {
        log.info("Input body data of user: {}", id);
        body.setId(id);
        body.setAge(accountService.getAgeById(id));
        try {
            bodyService.inputBody(body);
        } catch (Exception e) {
            log.info("Exception=========================");
            return Result.error(e.getMessage());
        }
        return Result.success();
    }
}
