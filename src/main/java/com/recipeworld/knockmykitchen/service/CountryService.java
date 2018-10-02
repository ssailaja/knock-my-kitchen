package com.recipeworld.knockmykitchen.service;

import com.recipeworld.knockmykitchen.models.Country;

import java.util.List;

public interface CountryService {

    Iterable<Country> findAll();

    Country findById(Integer countryId);
}
