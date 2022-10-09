package com.example.gradingsystem.services.user;

import com.example.gradingsystem.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUser(int id);
    List<User> getUserList();
    User updateUser(User user);
    String deleteUser(int id);

    User findByUsername(String name);
}
