package com.recipeworld.knockmykitchen.service.impl;

import com.recipeworld.knockmykitchen.models.Recipe;
import com.recipeworld.knockmykitchen.models.data.RecipeDao;
import com.recipeworld.knockmykitchen.service.CountryService;
import com.recipeworld.knockmykitchen.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeDao recipeDao;

    /**
     * Used to add new recipe...
     * @param recipe
     */
    @Override
    public void addRecipe(Recipe recipe) {
        recipeDao.save(recipe);
    }

    /**
     * Used to find available recipe by given id...
     * @param recipeId
     * @return Recipe
     */
    @Override
    public Recipe findById(Integer recipeId) {
        return recipeDao.findById(recipeId).get();
    }

    /**
     *
     * Used to delete existing recipe as per user request
     * @param recipe
     * @return booelan
     */
    @Override
    public boolean removeRecipe(Recipe recipe) {
        recipeDao.delete(recipe);
        return true;
    }

    @Override
    public boolean modifyRecipe(Recipe recipe) {
        recipeDao.save(recipe);
        return true;
    }

    /**
     * Used to get all avaialable recipes
     * @return List<Recipe>
     */
    @Override
    public List<Recipe> findAll() {
        List<Recipe> target = new ArrayList<>();
        recipeDao.findAll().forEach(target::add);
        return target;
    }

    /**
     * Used to get all avaialable recipes by given country
     * @return List<Recipe>
     */
    @Override
    public List<Recipe> findAllByCountryId(Integer countryId) {
        return recipeDao.findRecipesByCountryId(countryId);
    }
}