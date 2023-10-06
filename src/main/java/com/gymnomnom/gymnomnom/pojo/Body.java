package com.gymnomnom.gymnomnom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Body {
    private int id;
    private int age;
    private double height;
    private double weight;
    private double bmi;

}
