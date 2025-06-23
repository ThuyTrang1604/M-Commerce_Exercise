package Models;

public class Product {
    private final String id;
    private final String name;
    private final double price;
    private final String imageLink;

    public Product(String id, String name, double price, String imageLink) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageLink = imageLink;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImageLink() {
        return imageLink;
    }

    @Override
    public String toString() {
        return name;
    }
} 