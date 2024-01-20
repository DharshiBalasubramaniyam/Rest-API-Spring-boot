package com.example.demo.service;

import com.example.demo.modals.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User newUser, int id) {
        userRepository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setName(newUser.getName());
                    user.setPhone(newUser.getPhone());
                    return userRepository.save(user);
                });
    }

    @Override
    public void deleteUser(int id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}
