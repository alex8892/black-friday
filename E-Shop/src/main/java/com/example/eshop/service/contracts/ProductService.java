package com.example.eshop.service.contracts;

import com.example.eshop.models.Product;
import com.example.eshop.models.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    List<Product> getAllWithoutBlackFriday();

    List<Product> getAllBlackFriday();

    List<Product> getAll();

    Product getProductById(int id);

    void createProduct(ProductDto productDto);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    void saveDeleteProduct(Product product);

    boolean checkProductExist(String name);
}
