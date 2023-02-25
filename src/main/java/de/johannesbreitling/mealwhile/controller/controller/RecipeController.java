package de.johannesbreitling.mealwhile.controller.controller;

import de.johannesbreitling.mealwhile.controller.exceptions.EntityNotFoundException;
import de.johannesbreitling.mealwhile.controller.services.GroceryService;
import de.johannesbreitling.mealwhile.controller.services.RecipeService;
import de.johannesbreitling.mealwhile.controller.services.UserService;
import de.johannesbreitling.mealwhile.model.recipe.Recipe;
import de.johannesbreitling.mealwhile.model.requests.RecipeRequest;
import de.johannesbreitling.mealwhile.model.user.UserCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@SuppressWarnings("rawtypes")
public class RecipeController {

    private final GroceryService groceryService;
    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, GroceryService groceryService, UserService userService) {
        this.recipeService = recipeService;
        this.groceryService = groceryService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Recipe>> getRecipesByCategory(@PathVariable String id) {

        UserCategory category = userService.getUserCategoryById(id);

        if (category == null) {
            throw new EntityNotFoundException();
        }

        List<Recipe> recipes = recipeService.getRecipesByUserCategory(category);

        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity createRecipe(@RequestBody RecipeRequest recipe) {

        UserCategory category = userService.getUserCategoryById(recipe.userCategoryId());

        if (category == null) {
            throw new EntityNotFoundException();
        }

        Recipe newRecipe = new Recipe(recipe.name(), recipe.notes(), recipe.instructions(), category);

        return new ResponseEntity(HttpStatus.OK);
    }

}
