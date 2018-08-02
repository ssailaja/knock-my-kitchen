package com.recipeworld.knockmykitchen.controllers;

import com.recipeworld.knockmykitchen.models.Country;
import com.recipeworld.knockmykitchen.models.Recipe;
import com.recipeworld.knockmykitchen.models.User;
import com.recipeworld.knockmykitchen.models.data.UserDao;
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

    @Autowired
    private HttpSession session;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/signin")
    public String signin(Model model) {

        model.addAttribute(new User());
        model.addAttribute("title", "Sign In");

        return "user/login";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(@ModelAttribute @Valid User newUser,
                         Errors errors,
                         Model model) {

        if (errors.hasErrors()) {
            return "user/login";
        }
        // checking as existing user against DB value
        session.setAttribute("signedUser", true);
        model.addAttribute("signedUser", session.getAttribute("signedUser"));
        return "redirect:country";
    }
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
        /*User userExists = userDao.findByName(newUser.getUserName());
        if (userExists != null) {
            errors.rejectValue("userName", "error.user",
                            "There is already a user registered with the provided name");
        }*/
        if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {

            // errors.getAllErrors().add()
            return "user/signup";
        }
        if (errors.hasErrors()) {
            return "user/signup";
        }
        // checking as existing user against DB value
        return "redirect:country";
    }
}
