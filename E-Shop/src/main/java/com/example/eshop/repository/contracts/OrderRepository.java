package com.example.eshop.repository.contracts;

import com.example.eshop.models.Order;

import java.util.Date;
import java.util.List;

public interface OrderRepository {

    List<Order> getAll();

    List<Order> getFilterOrders(Date dateFrom, Date dateTo);

    Order getOrderById(int id);

    void createOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(Order order);
}
