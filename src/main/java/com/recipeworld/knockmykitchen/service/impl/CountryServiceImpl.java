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

    public static Integer id = 0;

    static {
        Country country = new Country("India");
        country.setId(id++);
        countries.add(country);
        country = new Country("America");
        country.setId(id++);
        countries.add(country);
        country = new Country("Mexico");
        country.setId(id++);
        countries.add(country);
        country = new Country("Australia");
        country.setId(id++);
        countries.add(country);
    }

    @Override
    public List<Country> findAll() {
        return countries;
    }

    @Override
    public Country findById(Integer countryId) {
        return countries.stream().filter(cn -> countryId.equals(cn.getId())).findAny().get();
    }
}
