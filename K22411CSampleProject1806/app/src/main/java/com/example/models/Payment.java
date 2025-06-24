package com.example.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Payment implements Serializable {
    private int id;
    private String name;
    private String description;
    private boolean isActive;

    public Payment() {
    }

    public Payment(int id, String name, String description, boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @NonNull
    @Override
    public String toString() {
        String info = id + "-" + name + "\n" + description;
        return info;
    }
} 