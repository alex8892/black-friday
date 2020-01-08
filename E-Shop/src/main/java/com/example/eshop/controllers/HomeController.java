package com.example.eshop.controllers;

import com.example.eshop.models.dtos.OrderDto;
import com.example.eshop.service.contracts.BlackFridayService;
import com.example.eshop.service.contracts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    private final ProductService productService;
    private final BlackFridayService blackFridayService;

    @Autowired
    public HomeController(ProductService productService, BlackFridayService blackFridayService) {
        this.productService = productService;
        this.blackFridayService = blackFridayService;
    }

    @GetMapping(value = {"/", "/home"})
    public ModelAndView showHomePage() {
        ModelAndView mav = new ModelAndView("home");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mav.addObject("products", productService.getAll());
        mav.addObject("order", new OrderDto());
        mav.addObject("blackFridayStarted", blackFridayService.isStarted());
        return mav;
    }

}
