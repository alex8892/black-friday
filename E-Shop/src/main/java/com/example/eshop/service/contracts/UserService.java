package com.example.eshop.service.contracts;

import com.example.eshop.models.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUserByUsername(String username);

    void updateUserById(User user);

    void deleteUser(User user);

}
