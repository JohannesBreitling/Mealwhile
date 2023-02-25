package de.johannesbreitling.mealwhile.model.requests;

public record RecipeRequest(String name, String notes, String instructions, String userCategoryId) { }