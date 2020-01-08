package com.example.eshop.service;

import com.example.eshop.models.BlackFriday;
import com.example.eshop.models.Product;
import com.example.eshop.models.dtos.ProductBlackFridayDto;
import com.example.eshop.service.contracts.BlackFridayService;
import com.example.eshop.service.contracts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlackFridayServiceImpl implements BlackFridayService {

    public static final boolean START = true;
    public static final boolean STOP = false;
    public static final int ADDED_TO_BLACK_FRIDAY = 1;
    public static final int NOT_ADDED_TO_BLACK_FRIDAY = 0;

    private final ProductService productService;
    private final BlackFriday blackFriday;

    @Autowired
    public BlackFridayServiceImpl(ProductService productService) {
        this.productService = productService;
        this.blackFriday = new BlackFriday();
    }


    @Override
    public boolean isStarted() {
        return blackFriday.isStarted();
    }

    @Override
    public void start() {
        blackFriday.setStarted(START);
    }

    @Override
    public void stop() {
        blackFriday.setStarted(STOP);
    }

    @Override
    public void addProduct(ProductBlackFridayDto productBlackFridayDto) {
        Product product = productService.getProductById(productBlackFridayDto.getProductId());
        product.setDiscount(productBlackFridayDto.getDiscount());
        Double reducedPrice = Math.max(product.getPrice()-(product.getPrice() * (product.getDiscount()) / 100.0), product.getMinPrice());
        product.setReducedPrice(reducedPrice);
        product.setAddedToBlackFriday(ADDED_TO_BLACK_FRIDAY);
        productService.updateProduct(product);
    }

    @Override
    public void removeProduct(int productId) {
        Product product = productService.getProductById(productId);
        product.setAddedToBlackFriday(NOT_ADDED_TO_BLACK_FRIDAY);
        product.setDiscount(0);
        product.setReducedPrice(0);
        productService.updateProduct(product);
    }
}
