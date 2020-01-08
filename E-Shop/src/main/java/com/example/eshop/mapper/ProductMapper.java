package com.example.eshop.mapper;

import com.example.eshop.models.Product;
import com.example.eshop.models.dtos.ProductDto;

public class ProductMapper {

    public static final int ENABLED = 1;

    public static void productDtoMapToProduct(ProductDto productDto, Product product) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setMinPrice(productDto.getMinPrice());
        product.setPrice(productDto.getPrice());
        product.setEnabled(ENABLED);
    }
}
