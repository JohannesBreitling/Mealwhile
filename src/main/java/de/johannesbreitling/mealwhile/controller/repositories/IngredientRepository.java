package de.johannesbreitling.mealwhile.controller.repositories;

import de.johannesbreitling.mealwhile.model.recipe.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findById(String id);

}
