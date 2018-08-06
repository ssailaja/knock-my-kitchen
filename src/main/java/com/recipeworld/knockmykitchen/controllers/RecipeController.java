package com.recipeworld.knockmykitchen.controllers;

import com.recipeworld.knockmykitchen.models.Country;
import com.recipeworld.knockmykitchen.models.Recipe;
import com.recipeworld.knockmykitchen.models.data.CountryDao;
import com.recipeworld.knockmykitchen.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private CountryDao countryDao;

    // Request path: /recipe
    @RequestMapping(value = "")
    public String displayRecipesForCountry(Model model, @RequestParam("id") int countryId) {
        System.out.println("Country ID " + countryId);
        model.addAttribute("title", "Recipes");
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe("Panneer Butter Masal", "Description", "Sailaja", new Date(), null, null);
        recipes.add(recipe);
        model.addAttribute("recipes", recipes);
        // model.addAttribute("recipes", recipeDao.findAllById(countryId));
        model.addAttribute("signedUser", true);
        return "recipe/index";
    }

    @RequestMapping(value = "/add")
    public String displayAddRecipeForm(Model model) {
        model.addAttribute("title", "Add Recipe");
        Recipe recipe = new Recipe();
        Country country = new Country("India");
        country.setId(1);
        recipe.setCountry(country);
        model.addAttribute("recipe", recipe);
        // model.addAttribute("countries", countryDao.findAll());
        model.addAttribute("countries", getCountries());
        return "recipe/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddRecipeForm(
            @ModelAttribute @Valid Recipe newRecipe,
            Errors errors,
            Model model) {
        if (errors.hasErrors()) {
            System.out.println("Inside recipe add post method in errors condition..... " + newRecipe.toString());

            // model.addAttribute("countries", countryDao.findAll());
            model.addAttribute("countries", getCountries());
            model.addAttribute("recipe", newRecipe);
            return "recipe/add";
        }
        Country country = countryDao.findById(newRecipe.getCountry().getId()).get();
        newRecipe.setCountry(country);
        recipeDao.save(newRecipe);
        return "redirect:";
    }

    @RequestMapping(value = "/modify/{recipeId}", method = RequestMethod.GET)
    public String displayReviewRecipeForm(Model model, @PathVariable String recipeId) {
        Recipe recipe = new Recipe("Panneer Butter Masal", "Description", "Sailaja", new Date(), null, null);

        //model.addAttribute("recipe", recipeDao.findById(recipeId));
        model.addAttribute("recipe", recipe);
        model.addAttribute("title", "Review Recipe");
        return "recipe/review";
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public String displayModifyRecipeForm(Model model) {
        model.addAttribute("recipes", recipeDao.findAll());
        model.addAttribute("title", "Remove Recipe");
        return "recipe/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveRecipeForm(@RequestParam int[] recipeIds) {

        for (int recipeId : recipeIds) {
            recipeDao.deleteById(recipeId);
        }

        return "redirect:";
    }

    private List<Country> getCountries() {
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
        return countries;
    }

}
