package com.example.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class PaymentMethod implements Serializable {
    private int id;
    private String methodName;
    private String description;

    public PaymentMethod() {
    }

    public PaymentMethod(int id, String methodName, String description) {
        this.id = id;
        this.methodName = methodName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " - " + methodName;
    }
}
