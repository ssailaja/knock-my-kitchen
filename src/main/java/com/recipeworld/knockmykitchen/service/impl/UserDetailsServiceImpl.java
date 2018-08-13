package com.recipeworld.knockmykitchen.service.impl;

import com.recipeworld.knockmykitchen.models.User;
import com.recipeworld.knockmykitchen.models.data.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    public static List<User> users = new ArrayList<>();

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    static {
        users.add(new User("shreyas", "1234"));
        users.add(new User("sahasra", "5678"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Inside UserDetailsServiceImpl --------------- loadUserByUsername method.........." + username);
        /*Optional<User> user = users.stream()
                .filter(u -> u.getUserName().equals(username))
                .findAny();*/
        User user = userDao.findByUserName(username);
        if (null == user) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
