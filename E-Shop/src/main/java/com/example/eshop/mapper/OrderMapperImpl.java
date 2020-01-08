package com.example.eshop.mapper;

import com.example.eshop.mapper.contracts.OrderMapper;
import com.example.eshop.models.Order;
import com.example.eshop.models.Product;
import com.example.eshop.models.dtos.OrderDto;
import com.example.eshop.service.contracts.ProductService;
import com.example.eshop.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class OrderMapperImpl implements OrderMapper {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public OrderMapperImpl(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }


    public Order orderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setDate(getCurrentDate());
        Product product = productService.getProductById(orderDto.getProductId());
        order.setProduct(product);
        order.setUser(userService.getUserByUsername(orderDto.getUser()));
        order.setPrice(orderDto.getQuantity() * orderDto.getPrice());
        reduceProductQuantity(product, orderDto.getQuantity());

        return order;
    }


    private Date getCurrentDate() {
        java.util.Date utilDate = new java.util.Date();
        return new java.sql.Date(utilDate.getTime());
    }

    private void reduceProductQuantity(Product product, int count) {
        product.setQuantity(product.getQuantity() - count);
        productService.updateProduct(product);
    }
}
