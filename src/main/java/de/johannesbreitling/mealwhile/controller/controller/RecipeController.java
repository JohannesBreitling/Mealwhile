package de.johannesbreitling.mealwhile.controller.controller;

import de.johannesbreitling.mealwhile.controller.exceptions.EntityNotFoundException;
import de.johannesbreitling.mealwhile.controller.exceptions.IllegalUnitException;
import de.johannesbreitling.mealwhile.controller.exceptions.IngredientAlreadyContainedException;
import de.johannesbreitling.mealwhile.controller.services.GroceryService;
import de.johannesbreitling.mealwhile.controller.services.RecipeService;
import de.johannesbreitling.mealwhile.controller.services.UserService;
import de.johannesbreitling.mealwhile.controller.utils.converter.IngredientConverter;
import de.johannesbreitling.mealwhile.model.grocery.Grocery;
import de.johannesbreitling.mealwhile.model.recipe.Ingredient;
import de.johannesbreitling.mealwhile.model.recipe.IngredientUnit;
import de.johannesbreitling.mealwhile.model.recipe.Recipe;
import de.johannesbreitling.mealwhile.model.requests.IngredientRequest;
import de.johannesbreitling.mealwhile.model.requests.RecipeRequest;
import de.johannesbreitling.mealwhile.model.requests.RemoveIngredientRequest;
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

        recipeService.saveRecipe(newRecipe);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateRecipe(@PathVariable String id, @RequestBody RecipeRequest recipe) {

        UserCategory category = userService.getUserCategoryById(recipe.userCategoryId());
        Recipe foundRecipe = recipeService.getRecipeById(id);

        if (category == null || foundRecipe == null) {
            throw new EntityNotFoundException();
        }

        Recipe newRecipe = new Recipe(recipe.name(), recipe.notes(), recipe.instructions(), category);
        recipeService.updateRecipe(foundRecipe, newRecipe);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity updateRecipe(@PathVariable String id) {

        Recipe recipe = recipeService.getRecipeById(id);

        if (recipe != null) {
            recipeService.deleteRecipe(recipe);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/ingredient/add/{recipeId}")
    public ResponseEntity addIngredient(@PathVariable String recipeId, @RequestBody IngredientRequest ingredient) {

        Recipe recipe = recipeService.getRecipeById(recipeId);
        Grocery grocery = groceryService.getGroceryById(ingredient.groceryId());
        System.out.println(ingredient.groceryId());
        System.out.println(grocery);
        if (recipe == null || grocery == null) {
            throw new EntityNotFoundException();
        }

        if (recipe.containsGrocery(grocery)) {
            throw new IngredientAlreadyContainedException();
        }

        try {
            IngredientUnit unit = IngredientConverter.convertUnitFromString(ingredient.unit());
            Ingredient newIngredient = new Ingredient(grocery, ingredient.quantity(), unit);
            recipeService.saveIngredient(newIngredient);
            recipe.addIngredient(newIngredient);
            recipeService.saveRecipe(recipe);

            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new IllegalUnitException();
        }

    }

    @PostMapping("ingredient/remove/{recipeId}")
    public ResponseEntity removeIngredient(@PathVariable String recipeId, @RequestBody RemoveIngredientRequest ingredient) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        Ingredient foundIngredient = recipeService.getIngredientById(ingredient.id());

        if (recipe == null || foundIngredient == null) {
            throw new EntityNotFoundException();
        }

        recipe.removeGrocery(foundIngredient.getGrocery());
        recipeService.saveRecipe(recipe);
        recipeService.deleteIngredient(foundIngredient);

        return new ResponseEntity(HttpStatus.OK);
    }


}
