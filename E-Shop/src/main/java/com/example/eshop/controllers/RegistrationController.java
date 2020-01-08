package com.example.eshop.controllers;


import com.example.eshop.models.User;
import com.example.eshop.service.contracts.BlackFridayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final BlackFridayService blackFridayService;

    @Autowired
    public RegistrationController(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder, BlackFridayService blackFridayService) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.blackFridayService = blackFridayService;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult errors, Model model) {

        if (errors.hasErrors()) {
            return "registration";
        }

        if (userDetailsManager.userExists(user.getUsername())) {
            model.addAttribute("error", "User with the same username already exists!");
            return "registration";
        }

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        passwordEncoder.encode(user.getPassword()),
                        authorities);
        userDetailsManager.createUser(newUser);

        return "/login";
    }

    @GetMapping("/register/employee")
    public ModelAndView showEmployeeRegisterPage() {
        ModelAndView mav = new ModelAndView("registration-employee");
        mav.addObject("user", new User());
        mav.addObject("blackFridayStarted", blackFridayService.isStarted());
        return mav;
    }

    @PostMapping("/register/employee")
    public String registerEmployee(@Valid @ModelAttribute User user, BindingResult errors, Model model) {

        if (userDetailsManager.userExists(user.getUsername()) || errors.hasErrors()) {
            model.addAttribute("error", "Employee with the same username already exists!");
            model.addAttribute("blackFridayStarted", blackFridayService.isStarted());
            return "registration-employee";
        }

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        passwordEncoder.encode(user.getPassword()),
                        authorities);
        userDetailsManager.createUser(newUser);
        return "redirect:/users";
    }
}