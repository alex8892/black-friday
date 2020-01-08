package com.example.eshop.controllers;

import com.example.eshop.models.Product;
import com.example.eshop.models.dtos.OrderDto;
import com.example.eshop.models.dtos.ProductDto;
import com.example.eshop.service.contracts.BlackFridayService;
import com.example.eshop.service.contracts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class ProductController {

    private final ProductService productService;
    private final BlackFridayService blackFridayService;

    @Autowired
    public ProductController(ProductService productService, BlackFridayService blackFridayService) {
        this.productService = productService;
        this.blackFridayService = blackFridayService;
    }

    @GetMapping(value = {"/products"})
    public ModelAndView showProducts() {
        ModelAndView mav = new ModelAndView("products");
        mav.addObject("products", productService.getAllWithoutBlackFriday());
        mav.addObject("order", new OrderDto());
        mav.addObject("blackFridayStarted", blackFridayService.isStarted());
        return mav;
    }

    @GetMapping(value = {"/products/black-friday"})
    public ModelAndView showHomePage() {
        ModelAndView mav = new ModelAndView("productsBlackFriday");
        mav.addObject("products", productService.getAllBlackFriday());
        mav.addObject("order", new OrderDto());
        mav.addObject("blackFridayStarted", blackFridayService.isStarted());
        return mav;
    }

    @GetMapping("/products/new")
    public ModelAndView showNewProductForm() {
        ModelAndView mav = new ModelAndView("createProduct");
        mav.addObject("product", new Product());
        mav.addObject("blackFridayStarted", blackFridayService.isStarted());
        return mav;
    }

    @PostMapping("/products/new")
    public String createProduct(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult errors,
                                Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("blackFridayStarted", blackFridayService.isStarted());
            return "createProduct";
        }

        if (productService.checkProductExist(productDto.getName())) {
            model.addAttribute("error", "Product with that name already exist!");
            model.addAttribute("blackFridayStarted", blackFridayService.isStarted());
            return "createProduct";
        }
        if (productDto.getPrice() < productDto.getMinPrice()) {
            model.addAttribute("error", "Price can not be less then minimum price!");
            model.addAttribute("blackFridayStarted", blackFridayService.isStarted());
            return "createProduct";
        }

        productService.createProduct(productDto);
        return "redirect:/home";
    }


    @GetMapping("/products/update/{id}")
    public ModelAndView showUpdateProductForm(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("updateProduct");
        Product product = productService.getProductById(id);
        mav.addObject("product", product);
        mav.addObject("blackFridayStarted", blackFridayService.isStarted());
        return mav;
    }

    @PostMapping(value = "/products/update")
    public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult errors,
                                Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("blackFridayStarted", blackFridayService.isStarted());
            return "updateProduct";
        }

        if (product.getPrice() < product.getMinPrice()) {
            model.addAttribute("error", "Price can not be less then the minimum price!");
            model.addAttribute("blackFridayStarted", blackFridayService.isStarted());
            return "updateProduct";
        }

        productService.updateProduct(product);

        return "redirect:/home";
    }

    @PostMapping(value = "/products/delete/{id}")
    public void deleteProduct(@PathVariable("id") int id, HttpServletResponse response) throws IOException {
        Product product = productService.getProductById(id);
        productService.saveDeleteProduct(product);
        response.sendRedirect("/home");
    }
}
