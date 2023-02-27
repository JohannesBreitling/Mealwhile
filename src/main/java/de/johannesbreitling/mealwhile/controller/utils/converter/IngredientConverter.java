package de.johannesbreitling.mealwhile.controller.utils.converter;

import de.johannesbreitling.mealwhile.model.recipe.IngredientUnit;

public class IngredientConverter {

    public static IngredientUnit convertUnitFromString(String unit) {
        return IngredientUnit.valueOf(unit);
    }

}
