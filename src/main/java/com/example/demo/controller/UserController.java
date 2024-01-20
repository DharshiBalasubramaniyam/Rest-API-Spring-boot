package com.example.demo.controller;
import com.example.demo.repository.UserRepository;
import com.example.demo.modals.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller used to define controllers.
// an endpoint is a method in controller.
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. add new user - user registration - POST
    @PostMapping("/new")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User newUser, @PathVariable int id) {
        userService.updateUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}


