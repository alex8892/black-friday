package com.example.eshop.service;

import com.example.eshop.models.User;
import com.example.eshop.repository.contracts.UserRepository;
import com.example.eshop.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void updateUserById(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

}
