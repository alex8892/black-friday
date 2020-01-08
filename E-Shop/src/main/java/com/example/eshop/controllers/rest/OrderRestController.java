package com.example.eshop.controllers.rest;

import com.example.eshop.models.Order;
import com.example.eshop.models.dtos.OrderDto;
import com.example.eshop.service.contracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{id}")
    public Order getById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public void createOrder(@RequestBody OrderDto order) {
        orderService.createOrder(order);
    }

    @PutMapping
    public void updateProduct(@RequestBody Order order) {
        orderService.updateOrder(order);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Order order) {
        orderService.deleteOrder(order);
    }

}
