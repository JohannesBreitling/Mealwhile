package de.johannesbreitling.mealwhile.controller.services;

import de.johannesbreitling.mealwhile.controller.repositories.IngredientRepository;
import de.johannesbreitling.mealwhile.controller.repositories.RecipeRepository;
import de.johannesbreitling.mealwhile.model.recipe.Ingredient;
import de.johannesbreitling.mealwhile.model.recipe.Recipe;
import de.johannesbreitling.mealwhile.model.user.UserCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public List<Recipe> getRecipesByUserCategory(UserCategory category) {
        return recipeRepository.findByUserCategory(category);
    }

    public Recipe getRecipeById(String id) {
        return recipeRepository.findById(id);
    }

    public void updateRecipe(Recipe oldRecipe, Recipe newRecipe) {
        oldRecipe.setName(newRecipe.getName());
        oldRecipe.setNotes(newRecipe.getNotes());
        oldRecipe.setInstructions(newRecipe.getInstructions());
        oldRecipe.setUserCategory(newRecipe.getUserCategory());

        recipeRepository.save(oldRecipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public Ingredient getIngredientById(String id) {
        return ingredientRepository.findById(id);
    }

    public void deleteIngredient(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }

}
