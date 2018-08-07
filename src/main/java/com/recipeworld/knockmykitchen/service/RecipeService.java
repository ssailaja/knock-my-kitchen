package com.recipeworld.knockmykitchen.service;

import com.recipeworld.knockmykitchen.models.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface RecipeService {


   void addRecipe(Recipe recipe);

   Recipe findById(Integer recipreId);

   boolean removeRecipe(Recipe recipe);

   boolean modifyRecipe(Recipe recipe);

   List<Recipe> findAll();

   List<Recipe> findAllByCountryId(Integer countryId);
}
