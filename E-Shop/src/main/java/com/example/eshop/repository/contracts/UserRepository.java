package com.example.eshop.repository.contracts;

import com.example.eshop.models.User;

import java.util.List;

public interface UserRepository {

    List<User> getAll();

    User getUserByUsername(String username);

    void updateUser(User user);

    void deleteUser(User user);

    void createUser(User user);

}
