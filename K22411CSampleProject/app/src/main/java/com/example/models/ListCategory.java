package com.example.models;

import java.util.ArrayList;

public class ListCategory {
    private ArrayList<Category> category;

    public ListCategory() {
        category = new ArrayList<>();
    }

    public void addCategory(Category ca) {
        category.add(ca);
    }

    public ArrayList<Category> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }

    public void generate_sample_dataset() {
        addCategory(new Category(1, "Kitchen"));
        addCategory(new Category(2, "Kitchen1"));
        addCategory(new Category(3, "Kitchen2"));
    }
}