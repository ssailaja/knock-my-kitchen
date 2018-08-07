package com.recipeworld.knockmykitchen.service.impl;

import com.recipeworld.knockmykitchen.models.User;
import com.recipeworld.knockmykitchen.models.data.UserDao;
import com.recipeworld.knockmykitchen.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.setRoles(new HashSet<>(roleRepository.findAll()));
        UserDetailsServiceImpl.users.add(user);
        LOGGER.info("The saved user info.............{}", UserDetailsServiceImpl.users);
        // userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        UserDetails details = userDetailsService.loadUserByUsername(username);
        // return getUser(username);
        return new User(details.getUsername(), details.getPassword());
    }

    @Override
    public void removeUser(String username) {
        // UserDetails details = getUserDetails(username);
        // userDao.delete(getUser(username));
        UserDetailsServiceImpl.users.remove(username);
    }

    private User getUser(String username) {
        return userDao.findByUserName(username);
    }
}
