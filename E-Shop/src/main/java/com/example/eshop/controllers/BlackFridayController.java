package com.example.eshop.controllers;

import com.example.eshop.models.dtos.ProductBlackFridayDto;
import com.example.eshop.service.contracts.BlackFridayService;
import com.example.eshop.service.contracts.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class BlackFridayController {

    private final BlackFridayService blackFridayService;
    private final ProductService productService;

    public BlackFridayController(BlackFridayService blackFridayService, ProductService productService) {
        this.blackFridayService = blackFridayService;
        this.productService = productService;
    }

    @GetMapping("/black-friday")
    public ModelAndView showBlackFridayProducts() {
        ModelAndView mav = new ModelAndView("blackFriday");
        mav.addObject("products", productService.getAll());
        mav.addObject("productDto", new ProductBlackFridayDto());
        mav.addObject("blackFridayStarted", blackFridayService.isStarted());
        return mav;
    }

    @GetMapping("/black-friday/start")
    public void startBlackFriday(HttpServletResponse response) throws IOException {
        blackFridayService.start();
        response.sendRedirect("/black-friday");
    }

    @GetMapping("/black-friday/stop")
    public void stopBlackFriday(HttpServletResponse response) throws IOException {
        blackFridayService.stop();
        response.sendRedirect("/home");
    }

    @PostMapping("/black-friday/add")
    public void addProduct(@ModelAttribute("productDto") ProductBlackFridayDto productBlackFridayDto, HttpServletResponse response) throws IOException {
        blackFridayService.addProduct(productBlackFridayDto);
        response.sendRedirect("/black-friday");
    }

    @PostMapping("/black-friday/remove/{id}")
    public void removeProduct(@PathVariable("id") int id, HttpServletResponse response) throws IOException {
        blackFridayService.removeProduct(id);
        response.sendRedirect("/black-friday");
    }
}
