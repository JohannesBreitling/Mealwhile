package de.johannesbreitling.mealwhile.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Grocery already exists")
public class GroceryAlreadyExistsException extends RuntimeException {

    public GroceryAlreadyExistsException() {
        super();
    }

}
