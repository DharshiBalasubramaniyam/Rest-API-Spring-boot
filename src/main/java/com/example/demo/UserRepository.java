package com.example.demo;

public interface UserRepository {

    public User findById(int id);
    public void save(User user);

    void remove(int id);

    void update(int id, User user);
}
