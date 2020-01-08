package com.example.eshop.service;

import com.example.eshop.mapper.contracts.OrderMapper;
import com.example.eshop.models.Order;
import com.example.eshop.models.dtos.OrderDto;
import com.example.eshop.repository.contracts.OrderRepository;
import com.example.eshop.service.contracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }


    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public List<Order> getFilterOrders(String dateRange) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String[] dates = dateRange.split("-");
        String dateFrom = dates[0].trim().replace("/", "-");
        String dateTo = dates[1].trim().replace("/", "-");

        java.util.Date dateF = null;
        java.util.Date dateT = null;

        try {
            dateF = dateFormat.parse(dateFrom);
            dateT = dateFormat.parse(dateTo);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        java.sql.Date sqlDateFrom = new java.sql.Date(dateF.getTime());
        java.sql.Date sqlDateTo = new java.sql.Date(dateT.getTime());

        return orderRepository.getFilterOrders(sqlDateFrom, sqlDateTo);
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public void createOrder(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        orderRepository.createOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.deleteOrder(order);
    }


}
