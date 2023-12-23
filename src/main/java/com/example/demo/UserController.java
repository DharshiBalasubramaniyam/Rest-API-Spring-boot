package com.example.demo;
import org.springframework.web.bind.annotation.*;

// controller used to define controllers.
// an endpoint is a method in controller.
@RestController
public class UserController {
    public UserRepository userRepository = new InMemoryUserRepository();

    //todo: add dependencies

    // 1. retrieving an existing user - GET
    @GetMapping("/user")
    public User getUser(@RequestParam int id){
        return userRepository.findById(id);
    }

    // 2. add new user - user registration - POST
    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    // 3. removing a user - DELETE
    @DeleteMapping("/user/{id}")
    public void removeUser(@PathVariable int id) {
        userRepository.remove(id);
    }

    // 4. update an existing user - PUT
    @PutMapping("user/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user){
        userRepository.update(id, user);
    }

}
