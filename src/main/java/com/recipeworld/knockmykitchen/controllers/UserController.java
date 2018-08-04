package com.recipeworld.knockmykitchen.controllers;

import com.recipeworld.knockmykitchen.models.Country;
import com.recipeworld.knockmykitchen.models.Recipe;
import com.recipeworld.knockmykitchen.models.User;
import com.recipeworld.knockmykitchen.models.data.UserDao;
import com.recipeworld.knockmykitchen.service.SecurityService;
import com.recipeworld.knockmykitchen.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/signin")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "user/login";
    }

    /*@RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(@ModelAttribute @Valid User newUser,
                         Errors errors,
                         Model model) {
        LOGGER.info("Inside signin post method...........");
        *//*if (errors.hasErrors()) {
            return "user/login";
        }*//*
        // checking as existing user against DB value
        session.setAttribute("signedUser", true);
        // model.addAttribute("signedUser", session.getAttribute("signedUser"));
        LOGGER.info("Inside signin post method........... Exits....");

        return "redirect:/country";
    }*/

    @RequestMapping(value = "/signup")
    public String signup(Model model) {

        model.addAttribute(new User());
        model.addAttribute("title", "Sign Up");

        return "user/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute @Valid User newUser,
                         Errors errors,
                         Model model) {

        if (errors.hasErrors()) {
            LOGGER.info("Inside error on signup post method " + newUser.getUserName());
            return "/user/signup";
        }

        userService.save(newUser);

        securityService.autologin(newUser.getUserName(), newUser.getPassword());
        // checking as existing user against DB value
        return "redirect:country";
    }
}
