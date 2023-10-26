package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.anno.Track;
import com.gymnomnom.gymnomnom.pojo.Body;
import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.service.BodyService;
import com.gymnomnom.gymnomnom.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class BodyController {

    @Autowired
    private BodyService bodyService;
    @Autowired
    private HttpServletRequest request;

    /**
     * Input the body data of today
     * @param body JSON of the body data
     * @return BMI
     */
    @PostMapping("/inputbody")
    @Track
    public Result inputBody(@RequestBody Body body) {
        //id
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");
        log.info("Input body data for user: {}", id);
        body.setId(id);

        try {
            bodyService.inputBody(body);
        } catch (Exception e) {
            log.info("Exception=========================");
            return Result.error(e.getMessage());
        }
        return Result.success(body.getBmi());
    }

    /**
     * Get the history body data
     * @return a map with heights, weights and BMI arraylists
     */
    @GetMapping("/history")
    @Track
    public Result getBodyHistory() {
        //id
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");
        log.info("Get body history data for user: {}", id);

        Map<String, ArrayList<Double>> bodyMap = new HashMap<>();
        bodyMap.put("heights", bodyService.getHeightArray(id));
        bodyMap.put("weights", bodyService.getWeightArray(id));
        bodyMap.put("BMI", bodyService.getBmiArray(id));
        return Result.success(bodyMap);
    }
}
