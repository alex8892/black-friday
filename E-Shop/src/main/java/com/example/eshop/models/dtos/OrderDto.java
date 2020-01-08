package com.example.eshop.models.dtos;

import javax.validation.constraints.Positive;

public class OrderDto {

    private String user;

    private int productId;

    @Positive(message = "Quantity has to be positive number.")
    private int quantity;

    private double price;

    public OrderDto() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
}
