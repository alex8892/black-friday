package com.example.eshop.mapper.contracts;

import com.example.eshop.models.Order;
import com.example.eshop.models.dtos.OrderDto;

public interface OrderMapper {

    Order orderDtoToOrder(OrderDto orderDto);

}
