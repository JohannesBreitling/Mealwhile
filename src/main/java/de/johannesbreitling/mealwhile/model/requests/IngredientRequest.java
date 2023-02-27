package de.johannesbreitling.mealwhile.model.requests;

public record IngredientRequest(String groceryId, float quantity, String unit) { }