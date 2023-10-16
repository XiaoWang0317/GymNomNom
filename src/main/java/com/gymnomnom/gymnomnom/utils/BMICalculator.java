package com.gymnomnom.gymnomnom.utils;

import com.gymnomnom.gymnomnom.pojo.Body;

public class BMICalculator {
    public static double bmiCalculator(Body body) {
        return body.getWeight() * 0.45359237 / (body.getHeight() * 0.0254 * body.getHeight() * 0.0254);
    }
}
