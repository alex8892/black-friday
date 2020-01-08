package com.example.eshop.models.dtos;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDto {

    public static final int MIN_NAME_LENGTH = 2;
    public static final int MAX_NAME_LENGTH = 15;
    public static final String NAME_MESSAGE_ERROR = "Name must be between 2 and 15!";
    public static final String QUANTITY_MESSAGE_ERROR = "Quantity con not be less than 1!";
    public static final int MIN_QUANTITY = 1;
    public static final String MIN_PRICE = "Price cannot be less than 1!";


    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = NAME_MESSAGE_ERROR)
    private String name;

    private String description;

    @Min(value = MIN_QUANTITY, message = QUANTITY_MESSAGE_ERROR)
    private int quantity;

    @Min(value = 1, message = MIN_PRICE)
    private double minPrice;

    @Min(value = 1, message = MIN_PRICE)
    private double price;

    public ProductDto() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
