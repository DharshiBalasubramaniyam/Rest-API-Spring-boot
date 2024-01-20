package com.example.demo.controller;
import com.example.demo.repository.UserRepository;
import com.example.demo.modals.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller used to define controllers.
// an endpoint is a method in controller.
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    public UserRepository userRepository;
    // constructor injection
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. add new user - user registration - POST
    @PostMapping("/new")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User newUser, @PathVariable int id) {
        userRepository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setName(newUser.getName());
                    user.setPhone(newUser.getPhone());
                    return userRepository.save(user);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}


