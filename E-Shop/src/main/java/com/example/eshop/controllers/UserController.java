package com.example.eshop.controllers;

import com.example.eshop.service.contracts.BlackFridayService;
import com.example.eshop.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;
    private final BlackFridayService blackFridayService;

    @Autowired
    public UserController(UserService userService, BlackFridayService blackFridayService) {
        this.userService = userService;
        this.blackFridayService = blackFridayService;
    }

    @GetMapping("/users")
    public ModelAndView showClients() {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", userService.getAll());
        mav.addObject("blackFridayStarted", blackFridayService.isStarted());
        return mav;
    }


    @DeleteMapping("/users/delete/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(userService.getUserByUsername(username));
    }

}
