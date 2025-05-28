package com.example.mynewapplication;

import java.util.ArrayList;
import java.util.List;
import com.example.mynewapplication.Account;

public class SimulationData {
    private static List<Product> productList = null;
    private static List<Account> accounts = null;
    public static List<Product> getProducts() {
        if (productList == null) {
            productList = new ArrayList<>();
            productList.add(new Product(1, "P001", "iPhone 15 Pro Max", 1299.99, "https://cdn.tgdd.vn/Products/Images/42/305660/iphone-15-pro-max-tu-nhien-1-750x500.jpg"));
            productList.add(new Product(2, "P002", "Samsung Galaxy S24 Ultra", 1199.99, "https://cdn.tgdd.vn/Products/Images/42/307174/samsung-galaxy-s24-ultra-xam-1-750x500.jpg"));
            productList.add(new Product(3, "P003", "OPPO Find X7 Ultra", 999.99, "https://cdn.tgdd.vn/Products/Images/42/309849/oppo-find-x7-600x600.jpg"));
            productList.add(new Product(4, "P004", "Xiaomi 14 Ultra", 899.99, "https://cdn.tgdd.vn/Products/Images/42/313889/xiaomi-14-ultra-white-thumbnew-600x600.jpg"));
            productList.add(new Product(5, "P005", "Vivo V30 Pro", 799.99, "https://cdn.tgdd.vn/Products/Images/42/214647/vivo-x30-pro-600x600-1-600x600.jpg"));
            productList.add(new Product(6, "P006", "realme 12 Pro+", 699.99, "https://cdn.tgdd.vn/Products/Images/42/278886/xiaomi-redmi-note-12-pro-5g-momo-1-600x600.jpg"));
            productList.add(new Product(7, "P007", "iPhone 14", 999.99, "https://cdn.tgdd.vn/Products/Images/42/289712/iPhone-14-plus-thumb-den-600x600.jpg"));
            productList.add(new Product(8, "P008", "Samsung Galaxy Z Fold5", 1499.99, "https://cdn.tgdd.vn/Products/Images/42/320721/samsung-galaxy-z-fold6-thumb-1-600x600.jpg"));
            productList.add(new Product(9, "P009", "OPPO Reno11 F", 499.99, "https://cdn.tgdd.vn/Products/Images/42/311354/oppo-a58-4g-green-thumb-600x600.jpg"));
            productList.add(new Product(10, "P010", "Xiaomi Redmi Note 13 Pro", 399.99, "https://cdn.tgdd.vn/Products/Images/42/328452/oppo-a3x-blue-thumb-600x600.jpg"));
            productList.add(new Product(11, "P011", "Vivo Y100", 349.99, "https://cdn.tgdd.vn/Products/Images/42/331200/vivo-y19s-thumbnew-600x600.jpg"));
            productList.add(new Product(12, "P012", "realme C67", 299.99, "https://cdn.tgdd.vn/Products/Images/42/326023/vivo-y28-xanh-thumbn-600x600.jpg"));
            productList.add(new Product(13, "P013", "iPhone 13", 799.99, "https://cdn.tgdd.vn/Products/Images/42/331985/vivo-v40-5g-thumb-600x600.jpg"));
            productList.add(new Product(14, "P014", "Samsung Galaxy A55", 599.99, "https://cdn.tgdd.vn/Products/Images/42/319214/vivo-v30-black-thumb-600x600.jpg"));
            productList.add(new Product(15, "P015", "OPPO A79", 399.99, "https://cdn.tgdd.vn/Products/Images/42/335177/samsung-galaxy-a56-5g-green-thumb-600x600.jpg"));
            productList.add(new Product(16, "P016", "Xiaomi Redmi 13C", 199.99, "https://cdn.tgdd.vn/Products/Images/42/240259/iPhone-14-plus-thumb-xanh-600x600.jpg"));
            productList.add(new Product(17, "P017", "Vivo Y18", 149.99, "https://cdn.tgdd.vn/Products/Images/42/335228/vivo-y04-green-thumb-600x600.jpg"));
            productList.add(new Product(18, "P018", "realme Note 50", 129.99, "https://cdn.tgdd.vn/Products/Images/42/334226/vivo-y29-trang-thumb-600x600.jpg"));
            productList.add(new Product(19, "P019", "Samsung Galaxy A15", 249.99, "https://cdn.tgdd.vn/Products/Images/42/328750/samsung-galaxy-a06-blue-thumbn-600x600.jpg"));
            productList.add(new Product(20, "P020", "OPPO A18", 99.99, "https://cdn.tgdd.vn/Products/Images/42/335174/samsung-galaxy-a36-5g-black-thumb-1-600x600.jpg"));
        }
        return productList;
    }
    public static void addProduct(Product p) {
        getProducts().add(p);
    }
    public static List<Account> getAccounts() {
        if (accounts == null) {
            accounts = new ArrayList<>();
            accounts.add(new Account(1, "admin", "admin"));
            accounts.add(new Account(2, "user1", "pass1"));
            accounts.add(new Account(3, "user2", "pass2"));
        }
        return accounts;
    }
} 