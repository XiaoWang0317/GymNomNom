package com.gymnomnom.gymnomnom.service.impl;

import com.gymnomnom.gymnomnom.mapper.AccountMapper;
import com.gymnomnom.gymnomnom.mapper.NutritionMapper;
import com.gymnomnom.gymnomnom.pojo.Diet;
import com.gymnomnom.gymnomnom.pojo.generalException;
import com.gymnomnom.gymnomnom.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NutritionServiceImpl implements NutritionService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private NutritionMapper nutritionMapper;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Input the diet
     * @param diet with id
     */
    @Override
    public void inputDiet(Diet diet) {
        //counter cases handling
        if (diet.getCalories() < 0 ||
                diet.getFat() < 0 ||
                diet.getCarbs() < 0 ||
                diet.getVc() < 0 ||
                diet.getVa() < 0 ||
                diet.getProtein() < 0) {
            throw new generalException("PLease input valid nutrition value");
        }

        //set values base on user information
        diet.setAge(accountMapper.getAgeById(diet.getId()));
        diet.setGender(accountMapper.getGenderById(diet.getId()));
        diet.setDate(LocalDateTime.now().format(formatter));

        //add
        nutritionMapper.inputDiet(diet);
    }

    @Override
    public List<Diet> getNutHistory(Integer id) {
        return nutritionMapper.getNutHistory(id);
    }

    @Override
    public Diet getDeitToday(Integer id) {
        Diet diet = nutritionMapper.getTodayDiet(LocalDateTime.now().format(formatter));
        return diet;
    }
}
