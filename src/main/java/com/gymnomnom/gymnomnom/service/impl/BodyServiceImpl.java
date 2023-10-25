package com.gymnomnom.gymnomnom.service.impl;

import com.gymnomnom.gymnomnom.mapper.BodyMapper;
import com.gymnomnom.gymnomnom.pojo.Body;
import com.gymnomnom.gymnomnom.pojo.generalException;
import com.gymnomnom.gymnomnom.service.BodyService;
import com.gymnomnom.gymnomnom.utils.BMICalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class BodyServiceImpl implements BodyService {
    @Autowired
    private BodyMapper bodyMapper;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Put the body data into the database
     * @param body a JSON required (height and weight)
     */
    @Override
    public void inputBody(Body body) {
        body.setDate(LocalDateTime.now().format(formatter));
        if (body.getWeight() <= 0 || body.getWeight() >= 1000) {
            throw new generalException("Please valid weight");
        }
        if (body.getHeight() <= 0 || body.getHeight() >= 100) {
            throw new generalException("Please valid height");
        }
        body.setBmi(BMICalculator.bmiCalculator(body));
        bodyMapper.inputBody(body);
    }

    /**
     * Get heights
     * @return arraylist
     */
    @Override
    public ArrayList<Double> getHeightArray(Integer id) {
        return bodyMapper.getHeights(id);
    }

    /**
     * Get weights
     * @return arraylist
     */
    @Override
    public ArrayList<Double> getWeightArray(Integer id) {
        return bodyMapper.getWeights(id);
    }

    /**
     * Get BMI
     * @return arraylist
     */
    @Override
    public ArrayList<Double> getBmiArray(Integer id) {
        return bodyMapper.getBmi(id);
    }
}
