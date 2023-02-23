package de.johannesbreitling.mealwhile.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Ingredient {

    private Grocery grocery;
    private float quantity;
    private IngredientUnit unit;

}