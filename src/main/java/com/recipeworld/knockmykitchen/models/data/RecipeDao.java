package com.recipeworld.knockmykitchen.models.data;

import com.recipeworld.knockmykitchen.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RecipeDao extends CrudRepository<Recipe, Integer> {
    public List<Recipe> findRecipesByCountryId(Integer countryId);

}
