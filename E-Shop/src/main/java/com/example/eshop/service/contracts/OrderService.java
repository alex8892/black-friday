package com.example.eshop.service.contracts;

import com.example.eshop.models.Order;
import com.example.eshop.models.dtos.OrderDto;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    List<Order> getFilterOrders(String dateRange);

    Order getOrderById(int id);

    void createOrder(OrderDto orderDto);

    void updateOrder(Order order);

    void deleteOrder(Order order);
}
