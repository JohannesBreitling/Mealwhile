package de.johannesbreitling.mealwhile.controller.repositories;

import de.johannesbreitling.mealwhile.model.recipe.Recipe;
import de.johannesbreitling.mealwhile.model.user.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findById(String id);
    List<Recipe> findByUserCategory(UserCategory category);

}
