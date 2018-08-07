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
    public String processAddRecipeForm(
            @ModelAttribute @Valid Recipe newRecipe,
            Errors errors,
            Model model) {
        if (errors.hasErrors()) {
            LOGGER.info("Inside recipe add post method in errors condition..... " + newRecipe.toString());

            model.addAttribute("recipe", newRecipe);
            return "recipe/add";
        }
        newRecipe.setId(RecipeServiceImpl.id++);
        recipeService.addRecipe(newRecipe);
        return "redirect:";
    }

    @RequestMapping(value = "/review/{recipeId}")
    public String displayReviewRecipeForm(Model model, @PathVariable Integer recipeId) {
        model.addAttribute("recipe", recipeService.findById(recipeId));
        model.addAttribute("title", "Review Recipe");
        return "recipe/review";
    }

    @RequestMapping(value = "/modify/{recipeId}")
    public String displayModifyRecipeForm(Model model, @PathVariable String recipeId) {
        Recipe recipe = recipeService.findById(Integer.valueOf(recipeId));
        model.addAttribute("recipe", recipe);
        model.addAttribute("title", "Modify Recipe");
        return "redirect:/recipe/add?countryId=" + recipe.getCountry().getId();
    }

    @RequestMapping(value = "/remove")
    public String processRemoveRecipeForm(@RequestParam Integer recipeId) {
        Recipe recipe = recipeService.findById(recipeId);
        Integer id = recipe.getCountry().getId();
        recipeService.removeRecipe(recipe);
        return "recipe/" + id;
    }


}
