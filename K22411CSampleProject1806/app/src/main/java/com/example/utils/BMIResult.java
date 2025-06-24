package com.example.utils;

public class BMIResult {
    private double BMI;
    private String Description;

    public BMIResult() {
    }
    public BMIResult(double BMI, String description) {
        this.BMI = BMI;
        Description = description;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
