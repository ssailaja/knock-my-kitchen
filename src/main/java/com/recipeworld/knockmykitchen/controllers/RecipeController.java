package com.recipeworld.knockmykitchen.controllers;

import com.recipeworld.knockmykitchen.models.Country;
import com.recipeworld.knockmykitchen.models.Recipe;
import com.recipeworld.knockmykitchen.models.data.CountryDao;
import com.recipeworld.knockmykitchen.models.data.RecipeDao;
import com.recipeworld.knockmykitchen.service.CountryService;
import com.recipeworld.knockmykitchen.service.RecipeService;
import com.recipeworld.knockmykitchen.service.impl.RecipeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CountryService countryService;

    // Request path: /recipe
    @RequestMapping(value = "/{id}")
    public String displayRecipesForCountry(Model model, @PathVariable("id") Integer countryId) {
        LOGGER.info("Country ID " + countryId);
        model.addAttribute("title", "Recipes");
        model.addAttribute("countryId", countryId);
        model.addAttribute("recipes", recipeService.findAllByCountryId(countryId));
        return "recipe/index";
    }

    @RequestMapping(value = "/add")
    public String displayAddRecipeForm(Model model, @RequestParam Integer countryId) {
        model.addAttribute("title", "Add Recipe");
        Recipe recipe = new Recipe();
        recipe.setCountry(countryService.findById(countryId));
        model.addAttribute("recipe", recipe);
        return "recipe/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddRecipeForm(Principal user, @RequestParam String countryId,
            @ModelAttribute @Valid Recipe newRecipe,
            Errors errors,
            Model model) {
        LOGGER.info("Country ID in recipe add POST method...........{}", countryId);
        LOGGER.info("Principal User in recipe add POST method...........{}", user.getName());
        if (errors.hasErrors()) {
            LOGGER.info("Inside recipe add post method in errors condition..... " + newRecipe.toString());

            model.addAttribute("recipe", newRecipe);
            return "recipe/add";
        }

        newRecipe.setId(RecipeServiceImpl.id++);
        newRecipe.setCreatedBy(user.getName());
        newRecipe.setCreatedOn(newRecipe.dateConversion(new Date()));
        newRecipe.setCountry(countryService.findById(Integer.valueOf(countryId)));
        LOGGER.info("Inside add POST method ........... {}", newRecipe.toString());
        recipeService.addRecipe(newRecipe);
        return "redirect:/country";
    }

    @RequestMapping(value = "/review/{recipeId}")
    public String displayReviewRecipeForm(Model model, @PathVariable Integer recipeId) {
        model.addAttribute("recipe", recipeService.findById(recipeId));
        model.addAttribute("title", "Review Recipe");
        return "recipe/review";
    }

    @RequestMapping(value = "/modify/{recipeId}")
    public String displayModifyRecipeForm(Model model, @PathVariable String recipeId) {
        model.addAttribute("recipe", recipeService.findById(Integer.valueOf(recipeId)));
        model.addAttribute("title", "Modify Recipe");
        return "recipe/add";
    }

    @RequestMapping(value = "/remove/{recipeId}")
    public String processRemoveRecipeForm(@PathVariable Integer recipeId) {
        LOGGER.info("Inside remove handler............{}", recipeId);
        Recipe recipe = recipeService.findById(recipeId);
        recipeService.removeRecipe(recipe);
        return "redirect:/country";
    }


}
