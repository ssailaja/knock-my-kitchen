package com.recipeworld.knockmykitchen.service.impl;

import com.recipeworld.knockmykitchen.models.Recipe;
import com.recipeworld.knockmykitchen.models.data.RecipeDao;
import com.recipeworld.knockmykitchen.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeDao recipeDao;

    public static List<Recipe> recipes = new ArrayList<>();

    static {
        recipes.add(new Recipe("Panneer Butter Masal", "Description", "Sailaja", new Date(), null, null));
        recipes.add(new Recipe("Briyani", "Chikkan Briyani", "Shreyas", new Date(), null, null));
    }

    @Override
    public void addRecipe(Recipe recipe) {
        // recipeDao.save(recipe);
        recipes.add(recipe);
    }

    @Override
    public Recipe findById(Integer recipreId) {
        // return recipeDao.findById(recipreId);
        return recipes.get(recipreId);
    }

    @Override
    public boolean removeRecipe(Recipe recipe) {
        // recipeDao.delete(recipe);
        return recipes.remove(recipe);
    }

    @Override
    public boolean modifyRecipe(Recipe recipe) {
        // recipeDao.save(recipe);
        return recipes.add(recipe);
    }

    @Override
    public List<Recipe> findAll() {
        return recipes;
    }
}
