package com.example.eshop.controllers.rest;

import com.example.eshop.models.User;
import com.example.eshop.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUserById(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }
}
