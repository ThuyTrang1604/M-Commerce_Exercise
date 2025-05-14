package com.example.models;

import java.util.ArrayList;

public class ListProduct {
    private ArrayList<Product> products;

    public ListProduct() {
        products = new ArrayList<>();
    }
    public void addProduct(Product p){
        products.add(p);
    }

    public ArrayList<Product> getProduct() {
        return products;
    }

    public void setProduct(ArrayList<Product> products) {
        this.products = products;
    }
    public void generate_sample_dataset(){
        addProduct(new Product(1, "Bàn", 20, 500000, 1 ));
        addProduct(new Product(2, "Ghế", 20, 500000, 2 ));
        addProduct(new Product(3, "Chén", 20, 500000, 3 ));
        addProduct(new Product(4, "Đũa", 20, 500000, 2 ));
        addProduct(new Product(5, "Muỗng", 20, 500000, 3 ));
    }
}