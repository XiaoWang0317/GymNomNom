package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.anno.Track;
import com.gymnomnom.gymnomnom.pojo.Body;
import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.service.AccountService;
import com.gymnomnom.gymnomnom.service.BodyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class BodyController {

    @Autowired
    private BodyService bodyService;
    @Autowired
    private AccountService accountService;

    /**
     * Input the body data of today
     * @param id of the user, from url
     * @param body JSON of the body data
     * @return BMI
     */
    @PostMapping("/{id}/inputbody")
    @Track
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
        return Result.success(body.getBmi());
    }

    /**
     * Get the history body data
     * @param id in the url
     * @return a map with heights, weights and BMI arraylists
     */
    @GetMapping("/{id}/history")
    @Track
    public Result getBodyHistory(@PathVariable Integer id) {
        log.info("Get the body history of ");
        Map<String, ArrayList<Double>> bodyMap = new HashMap<>();
        bodyMap.put("heights", bodyService.getHeightArray(id));
        bodyMap.put("weights", bodyService.getWeightArray(id));
        bodyMap.put("BMI", bodyService.getBmiArray(id));
        return Result.success(bodyMap);
    }
}
