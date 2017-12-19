package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model;

import java.util.UUID;

/**
 * Created by Sergey-PC on 04.12.2017.
 */

public class Product {
    private UUID id;
    private String productName;
    private double price;
    private int quantity;
    private String category;

    public Product(String productName, double price, int quantity, String category) {
        this.id = UUID.randomUUID();
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Product(UUID id) {
        this.id = id;
    }

    public Product() {
        this(UUID.randomUUID());
    }


    public UUID getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
