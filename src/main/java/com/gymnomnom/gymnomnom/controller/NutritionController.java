package com.gymnomnom.gymnomnom.controller;

import com.gymnomnom.gymnomnom.anno.Track;
import com.gymnomnom.gymnomnom.pojo.Diet;
import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.service.NutritionService;
import com.gymnomnom.gymnomnom.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

}
