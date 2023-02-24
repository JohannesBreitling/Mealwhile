package de.johannesbreitling.mealwhile.model.requests;


import de.johannesbreitling.mealwhile.model.UserCategory;

public record UserRequest(String name, String password, UserCategory category) { }
