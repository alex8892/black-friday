package com.example.eshop.models.dtos;

public class ProductBlackFridayDto {

    private int productId;

    private int discount;

    public ProductBlackFridayDto() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
