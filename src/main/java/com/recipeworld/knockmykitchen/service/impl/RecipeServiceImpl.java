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

    public static List<Recipe> recipes = new ArrayList<>();

    public static Integer id = 0;

    static {
        Recipe recipe = new Recipe("Panneer Butter Masal", "Description", "Sailaja", new Date(), null, null);
        recipe.setId(id++);
        recipe.setCountry(CountryServiceImpl.countries.get(0));
        recipes.add(recipe);
        recipe = new Recipe("Briyani", "Chikkan Briyani", "Shreyas", new Date(), null, null);
        recipe.setId(id++);
        recipe.setCountry(CountryServiceImpl.countries.get(1));
        recipes.add(recipe);
        recipe = new Recipe("Fish Fry", "Fish Fry", "Sakthivel", new Date(), null, null);
        recipe.setId(id++);
        recipe.setCountry(CountryServiceImpl.countries.get(2));
        recipes.add(recipe);
        recipe = new Recipe("Laddu", "Sweet", "Sahasra", new Date(), null, null);
        recipe.setId(id++);
        recipe.setCountry(CountryServiceImpl.countries.get(3));
        recipes.add(recipe);
        recipe = new Recipe("Burger", "Western", "Steve", new Date(), null, null);
        recipe.setId(id++);
        recipe.setCountry(CountryServiceImpl.countries.get(3));
        recipes.add(recipe);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        // recipeDao.save(recipe);
        recipes.add(recipe);
    }

    @Override
    public Recipe findById(Integer recipreId) {
        // return recipeDao.findById(recipreId);
        return recipes.stream().filter(re -> recipreId.equals(re.getId())).findAny().get();
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

    @Override
    public List<Recipe> findAllByCountryId(Integer countryId) {
        return recipes.stream().filter(re -> countryId.equals(re.getCountry().getId()))
                .collect(Collectors.toList());
    }
}
