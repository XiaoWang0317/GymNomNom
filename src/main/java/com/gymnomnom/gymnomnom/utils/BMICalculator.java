package com.gymnomnom.gymnomnom.utils;

import com.gymnomnom.gymnomnom.pojo.Body;

public class BMICalculator {
    public static double bmiCalculator(Body body) {
        return body.getWeight() / (body.getHeight() * body.getHeight());
    }
}
