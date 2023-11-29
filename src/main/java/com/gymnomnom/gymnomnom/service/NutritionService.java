package com.gymnomnom.gymnomnom.service;

import com.gymnomnom.gymnomnom.pojo.Diet;

import java.util.List;

public interface NutritionService {

    void inputDiet(Diet diet);

    List<Diet> getNutHistory(Integer id);

    Diet getDeitToday(Integer id);
}
