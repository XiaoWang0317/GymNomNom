package com.gymnomnom.gymnomnom.utils;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NutritionCalculator {
    public int nutritions(int[] nutrition, int age, int sex, int fitnessType){
        int[] recommendedTemp;
        // {fat, vc, va, calories, protein, carbs}
        if (age < 19){
            if (sex == 0){
                int[] recommended = {30, 65, 700, 1800, 46, 130};
                recommendedTemp = recommended;
            }
            else{
                int[] recommended = {30, 75, 900, 2800, 52, 130};
                recommendedTemp = recommended;
            }
        }
        else if (age < 31){
            if (sex == 0){
                int[] recommended = {28, 75, 700, 2000, 46, 130};
                recommendedTemp = recommended;
            }
            else{
                int[] recommended = {28, 90, 900, 2600, 56, 130};
                recommendedTemp = recommended;
            }
        }
        else if (age < 51){
            if (sex == 0){
                int[] recommended= {28, 75, 700, 1800, 46, 130};
                recommendedTemp = recommended;
            }
            else{
               int[] recommended = {28, 90, 900, 2200, 56, 130};
               recommendedTemp = recommended;
            }
        }
        else {
            if (sex == 0){
                int[] recommended = {28, 75, 700, 1600, 46, 130};
                recommendedTemp = recommended;
            }
            else{
                int[] recommended = {28, 90, 900, 2000, 56, 130};
                recommendedTemp = recommended;
            }
        }
   

        double vcPct = nutrition[1]/recommendedTemp[1];
        double vaPct = nutrition[2]/recommendedTemp[2];
        double proteinPct = nutrition[4]/recommendedTemp[4];
        if (fitnessType == 1){
            // More protein if want to gain muscle 
            proteinPct = proteinPct - 5.0;
        }
        double carbsPct = nutrition[5]/recommendedTemp[5];

        double[] v = new double[4];
        v[0] = nutrition[1]/recommendedTemp[1];
        v[1] = nutrition[2]/recommendedTemp[2];
        v[2] = nutrition[4]/recommendedTemp[4];
        v[3] = nutrition[5]/recommendedTemp[5];
        System.out.println(v);
        Arrays.sort(v);

        int nutrient = 0;

        if (v[0] == vcPct){
            nutrient = 0;
        }
        if (v[0] == vaPct){
            nutrient = 1;
        }
        if (v[0] == proteinPct){
            nutrient = 2;
        }
        if (v[0] == carbsPct){
            nutrient = 3;
        }

        return nutrient;
        
        /* 
        if(nutrient == 0){
            return "Consider eating fruits or vegetables to get some more Vitamin C. Some examples are strawberries, broccoli, tomatoes, and oranges.";
        }
        else if(nutrient == 1){
            return "You may want to eat leafy green vegetables, fish oils, milk or eggs to increase Vitamin A.";
        }
        else if(nutrient == 2){
            return "Eating some more protein would be good for you. Consider eating eggs, meat, nuts, or dairy.";
        }
        else if(nutrient == 3){
            return "You may benefit from eating some more carbs. You can get carbs from food like bread, rice, vegetables, and fruit.";
        }
        else{
            throw new IllegalArgumentException("The nutrient values entered are not valid.");
        }
        */
    }

    public static void main (String[] args){
        int[] i = {30, 85, 700, 1800, 46, 130};
        NutritionCalculator calculator = new NutritionCalculator();
        int s = calculator.nutritions(i, 52, 0,1);
        int[] i1 = {30, 85, 500, 2000, 96, 130};
        int s1 = calculator.nutritions(i1, 34, 0,1);
        int[] i2 = {60, 55, 700, 1800, 46, 130};
        int s2 = calculator.nutritions(i2, 18, 0,1);
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
    }
}
