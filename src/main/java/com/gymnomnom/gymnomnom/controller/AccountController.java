package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.anno.Track;
import com.gymnomnom.gymnomnom.mapper.LoginMapper;
import com.gymnomnom.gymnomnom.pojo.LoginLog;
import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.pojo.User;
import com.gymnomnom.gymnomnom.service.AccountService;
import com.gymnomnom.gymnomnom.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LoginMapper loginMapper;

    /**
     * Sign up a account
     * @param user a JSON required (all information of the user)
     * @return Result
     */
    @PostMapping("/signup")
    public Result signup(@RequestBody User user) {
        log.info("Add a user to database {}", user);
        try {
            accountService.add(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    /**
     * Delete an account
     * @return Result
     */
    @DeleteMapping("/delete")
    @Track
    public Result delete() {
        //id
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");
        log.info("Delete an account based on the id: {}", id);

        accountService.delete(id);
        return Result.success();
    }

    /**
     * Log in to the account
     * @param user a JSON required (name & password)
     * @return a map contains the user's id and the JWT
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("Log in: {}", user);
        User u = accountService.login(user);
        //success
        if (u != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("name", u.getName());
            claims.put("password", u.getPassword());
            claims.put("id", u.getId());
            String jwt = JwtUtils.generateJwt(claims);
            Map<String, Object> map = new HashMap<>();
            map.put("ID", u.getId());
            map.put("JWT", jwt);
            LoginLog login = new LoginLog(LocalDateTime.now(), u.getName(), u.getPassword(), u.getId());
            loginMapper.insert(login);
            return Result.success(map);
        }
        //fail
        return Result.error("Cannot find the name or password");
    }

}
