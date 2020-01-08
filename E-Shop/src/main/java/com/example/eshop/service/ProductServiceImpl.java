package com.example.eshop.service;

import com.example.eshop.mapper.ProductMapper;
import com.example.eshop.models.Product;
import com.example.eshop.models.dtos.ProductDto;
import com.example.eshop.repository.contracts.ProductRepository;
import com.example.eshop.service.contracts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    public static final int UN_ENABLED = 0;
    public static final int NOT_ADDED_TO_BLACK_FRIDAY = 0;

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllWithoutBlackFriday() {
        return productRepository.getAllWithoutBlackFriday();
    }

    @Override
    public List<Product> getAllBlackFriday() {
        return productRepository.getAllBlackFriday();
    }


    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }


    @Override
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = new Product();
        ProductMapper.productDtoMapToProduct(productDto, product);
        product.setAddedToBlackFriday(NOT_ADDED_TO_BLACK_FRIDAY);
        productRepository.createProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        product.setEnabled(1);
        productRepository.updateProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.deleteProduct(product);
    }

    @Override
    public void saveDeleteProduct(Product product) {
        product.setEnabled(UN_ENABLED);
        productRepository.saveDeleteProduct(product);
    }

    @Override
    public boolean checkProductExist(String name) {
        return productRepository.checkProductExist(name);
    }
}
