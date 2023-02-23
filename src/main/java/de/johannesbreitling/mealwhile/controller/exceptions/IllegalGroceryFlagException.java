package de.johannesbreitling.mealwhile.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Unknown GroceryFlag")
public class IllegalGroceryFlagException extends RuntimeException {

    public IllegalGroceryFlagException() {
        super();
    }

}
