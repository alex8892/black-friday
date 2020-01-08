package com.example.eshop.service.contracts;

import com.example.eshop.models.BlackFriday;
import com.example.eshop.models.dtos.ProductBlackFridayDto;

public interface BlackFridayService {

    boolean isStarted();
    void start();

    void stop();

    void addProduct(ProductBlackFridayDto productBlackFridayDto);

    void removeProduct(int productId);
}
