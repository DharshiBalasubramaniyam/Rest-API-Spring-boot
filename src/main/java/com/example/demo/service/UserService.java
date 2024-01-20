package com.example.demo.service;

import com.example.demo.modals.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> getAllUsers();

    void updateUser(User user, int id);

    void deleteUser(int id);
}
