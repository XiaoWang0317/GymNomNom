package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.anno.Track;
import com.gymnomnom.gymnomnom.pojo.Diet;
import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.service.AccountService;
import com.gymnomnom.gymnomnom.service.NutritionService;
import com.gymnomnom.gymnomnom.utils.JwtUtils;
import com.gymnomnom.gymnomnom.utils.NutritionCalculator;
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
    @Autowired
    private NutritionCalculator nutritionCalculator;
    @Autowired
    private AccountService accountService;

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

    @GetMapping("/recom")
    @Track
    public Result generateRecommendation() {
        //id
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");
        log.info("Get nutrition Recommendation for user: {}", id);


//        double[] nut = new double[6];
        int[] nut = new int[6];
        Diet diet = nutritionService.getDeitToday(id);
        nut[0] = (int)diet.getFat();
        nut[1] = (int)diet.getVc();
        nut[2] = (int)diet.getVa();
        nut[3] = (int)diet.getCalories();
        nut[4] = (int)diet.getProtein();
        nut[5] = (int)diet.getCarbs();

        int recom = nutritionCalculator.nutritions(nut,
                                       diet.getAge(),
                                       diet.getGender(),
                                       accountService.getFitnessTypeById(diet.getId()));

        return Result.success(recom);
    }

}
