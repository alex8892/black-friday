package com.example.eshop.controllers.rest;

import com.example.eshop.models.Product;
import com.example.eshop.models.dtos.ProductDto;
import com.example.eshop.service.contracts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) throws IOException {
        productService.createProduct(productDto);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
    }

}
