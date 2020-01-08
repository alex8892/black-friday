package com.example.eshop.repository.contracts;

import com.example.eshop.models.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAllWithoutBlackFriday();

    List<Product> getAllBlackFriday();

    List<Product> getAll();

    Product getProductById(int id);

    void createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    void saveDeleteProduct(Product product);

    boolean checkProductExist(String name);

}
