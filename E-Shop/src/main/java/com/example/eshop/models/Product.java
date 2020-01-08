package com.example.eshop.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    public static final int MIN_NAME_LENGTH = 2;
    public static final int MAX_NAME_LENGTH = 15;
    public static final String NAME_MESSAGE_ERROR = "Name must be between 2 and 15!";
    public static final String QUANTITY_MESSAGE_ERROR = "Quantity con not be less than 1!";
    public static final int MIN_QUANTITY = 1;
    public static final String MIN_PRICE = "Price cannot be less than 1!";


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @NotNull
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = NAME_MESSAGE_ERROR)
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    @Min(value = MIN_QUANTITY, message = QUANTITY_MESSAGE_ERROR)
    private int quantity;

    @Min(value = 1, message = MIN_PRICE)
    @Column(name = "minPrice")
    private double minPrice;

    @Min(value = 1, message = MIN_PRICE)
    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private int discount;

    @Column(name = "addedToBlackFriday")
    private int addedToBlackFriday;

    @Column(name = "reducedPrice")
    private double reducedPrice;

    @Column(name = "enabled")
    private int enabled;

    public Product() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int count) {
        this.quantity = count;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getAddedToBlackFriday() {
        return addedToBlackFriday;
    }

    public void setAddedToBlackFriday(int addedToBlackFriday) {
        this.addedToBlackFriday = addedToBlackFriday;
    }

    public double getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(double reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
