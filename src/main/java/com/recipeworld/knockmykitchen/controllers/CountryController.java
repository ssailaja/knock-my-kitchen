package com.recipeworld.knockmykitchen.controllers;

import com.recipeworld.knockmykitchen.models.Country;
import com.recipeworld.knockmykitchen.models.Recipe;
import com.recipeworld.knockmykitchen.models.data.CountryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("country")
public class CountryController {
    @Autowired
    private CountryDao countryDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        List<Country> countries = new ArrayList<>();
        Country country = new Country("India");
        country.setId(1);
        countries.add(country);
        country = new Country("America");
        country.setId(2);
        countries.add(country);
        country = new Country("Mexico");
        country.setId(3);
        countries.add(country);
        country = new Country("Australia");
        country.setId(4);
        countries.add(country);
        // model.addAttribute("countries", countryDao.findAll());
        model.addAttribute("countries", countries)
                .addAttribute("title", "Knock My Kitchen");

        return "country/index";
    }

}

