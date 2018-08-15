package com.recipeworld.knockmykitchen.service.impl;

import com.recipeworld.knockmykitchen.models.Country;
import com.recipeworld.knockmykitchen.models.data.CountryDao;
import com.recipeworld.knockmykitchen.service.CountryService;
import com.recipeworld.knockmykitchen.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    /**
     * Used to retrieve all available countries...
     * @return List<Country>
     */
    @Override
    public List<Country> findAll() {
        return Utility.iterableToCollection(countryDao.findAll());
    }

    /**
     * Used to get single country info on given id...
     * @param countryId
     * @return Country
     */
    @Override
    public Country findById(Integer countryId) {
        return countryDao.findById(countryId).get();
    }
}
