package com.recipeworld.knockmykitchen.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
