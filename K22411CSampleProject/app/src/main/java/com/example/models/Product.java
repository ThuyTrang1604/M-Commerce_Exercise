package com.example.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product  implements Serializable {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int cateid;


    public Product(int id, String name, int quantity, double price, int cateid) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.cateid = cateid;
    }
    @NonNull
    @Override
    public String toString() {
        String infor=id+"-"+name+"\n"+"Quantity: "+quantity+"\n"+"Price: "+price;
        return infor;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }
}