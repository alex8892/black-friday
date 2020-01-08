package com.example.eshop.controllers;

import com.example.eshop.models.dtos.OrderDto;
import com.example.eshop.service.contracts.BlackFridayService;
import com.example.eshop.service.contracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final BlackFridayService blackFridayService;

    @Autowired
    public OrderController(OrderService orderService, BlackFridayService blackFridayService) {
        this.orderService = orderService;
        this.blackFridayService = blackFridayService;
    }


    @GetMapping(value = "/orders")
    public ModelAndView showOrders() {
        ModelAndView mav = new ModelAndView("orders");
        mav.addObject("orders", orderService.getAll());
        mav.addObject("blackFridayStarted", blackFridayService.isStarted());
        return mav;
    }

    @GetMapping(value = "/orders/filtered")
    public ModelAndView showFilteredOrders(@RequestParam String daterange) {
        ModelAndView mav = new ModelAndView("orders");
        mav.addObject("orders", orderService.getFilterOrders(daterange));
        mav.addObject("blackFridayStarted", blackFridayService.isStarted());
        return mav;
    }


    @PostMapping(value = "/orders/new")
    public void createOrder(@ModelAttribute("order") OrderDto orderDto, HttpServletResponse response, Principal principal) throws IOException {
        orderDto.setUser(principal.getName());
        orderService.createOrder(orderDto);
        response.sendRedirect("/home");
    }
}
