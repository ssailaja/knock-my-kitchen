package com.recipeworld.knockmykitchen.service;


import com.recipeworld.knockmykitchen.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    void removeUser(String username);
}
