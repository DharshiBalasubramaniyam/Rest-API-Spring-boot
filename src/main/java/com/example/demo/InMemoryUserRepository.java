package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository{

    Map<Integer, User> userMap = new HashMap<>();

    public InMemoryUserRepository() {
        userMap.put(0, new User(0, "John", 25, "0785469358"));
        userMap.put(1, new User(1, "Mary", 25, "0748512596"));
        userMap.put(2, new User(2, "Geetha", 25, "0741258963"));
        userMap.put(3, new User(3, "Maya", 25, "0725896358"));
    }
    @Override
    public User findById(int id) {
        return userMap.get(id);
    }

    @Override
    public void save(User user) {
        userMap.put(user.id, user);
    }

    @Override
    public void remove(int id) {
        userMap.remove(id);
    }

    public void update(int id, User user) {
        userMap.remove(id);
        userMap.put(id, user);
    }
}
