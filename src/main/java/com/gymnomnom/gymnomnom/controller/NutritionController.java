package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.anno.Track;
import com.gymnomnom.gymnomnom.pojo.Diet;
import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.service.NutritionService;
import com.gymnomnom.gymnomnom.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class NutritionController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private NutritionService nutritionService;

    /**
     * Input the diet of today
     * @param diet with different nutrition in JSON
     * @return Result
     */
    @PostMapping("/inputnut")
    @Track
    public Result inputDiet(@RequestBody Diet diet) {
        //id
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");
        log.info("Input body data for user: {}", id);
        diet.setId(id);

        log.info("Protein: {}", diet.getProtein());

        //add
        try{
            nutritionService.inputDiet(diet);
        } catch (Exception e) {
            log.info("Exception=========================");
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    @GetMapping("/historynut")
    @Track
    public Result getNutritionHistory() {
        //id
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");
        log.info("Get nutrition data for user: {}", id);

        List<Diet> diet_list = nutritionService.getNutHistory(id);
        Map<String, List<Double>> nut_map = new HashMap<>();
        nut_map.put("fat", new ArrayList<>());
        nut_map.put("vc", new ArrayList<>());
        nut_map.put("va", new ArrayList<>());
        nut_map.put("calories", new ArrayList<>());
        nut_map.put("protein", new ArrayList<>());
        nut_map.put("carbs", new ArrayList<>());

        for (Diet diet : diet_list) {
            nut_map.get("fat").add(diet.getFat());
            nut_map.get("vc").add(diet.getVc());
            nut_map.get("va").add(diet.getVa());
            nut_map.get("calories").add(diet.getCalories());
            nut_map.get("protein").add(diet.getProtein());
            nut_map.get("carbs").add(diet.getCarbs());
        }
        return Result.success(nut_map);
    }

}
