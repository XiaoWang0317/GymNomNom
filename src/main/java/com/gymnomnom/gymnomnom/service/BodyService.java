package com.gymnomnom.gymnomnom.service;

import com.gymnomnom.gymnomnom.pojo.Body;

import java.util.ArrayList;

public interface BodyService {
    void inputBody(Body body);

    ArrayList<Double> getHeightArray(Integer id);

    ArrayList<Double> getWeightArray(Integer id);

    ArrayList<Double> getBmiArray(Integer id);
}
