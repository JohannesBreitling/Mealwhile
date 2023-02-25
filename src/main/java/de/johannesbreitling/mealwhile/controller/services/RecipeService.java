package de.johannesbreitling.mealwhile.controller.services;

import de.johannesbreitling.mealwhile.controller.repositories.RecipeRepository;
import de.johannesbreitling.mealwhile.model.recipe.Recipe;
import de.johannesbreitling.mealwhile.model.user.UserCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public List<Recipe> getRecipesByUserCategory(UserCategory category) {
        return recipeRepository.findByUserCategory(category);
    }

}
