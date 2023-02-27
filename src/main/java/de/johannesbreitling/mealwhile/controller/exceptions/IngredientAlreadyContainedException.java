package de.johannesbreitling.mealwhile.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Ingredient already contained in recipe")
public class IngredientAlreadyContainedException extends RuntimeException {

    public IngredientAlreadyContainedException() {
        super();
    }

}
