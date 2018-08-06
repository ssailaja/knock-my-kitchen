package com.recipeworld.knockmykitchen.service.impl;

import com.recipeworld.knockmykitchen.models.Country;
import com.recipeworld.knockmykitchen.models.data.CountryDao;
import com.recipeworld.knockmykitchen.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    public static List<Country> countries = new ArrayList<>();

    static {
        Country country = new Country("India");
        country.setId(0);
        countries.add(country);
        country = new Country("America");
        country.setId(1);
        countries.add(country);
        country = new Country("Mexico");
        country.setId(2);
        countries.add(country);
        country = new Country("Australia");
        country.setId(3);
        countries.add(country);
    }

    @Override
    public List<Country> findAll() {
        return countries;
    }

    @Override
    public Country findById(Integer countryId) {
        return countries.get(countryId);
    }
}
