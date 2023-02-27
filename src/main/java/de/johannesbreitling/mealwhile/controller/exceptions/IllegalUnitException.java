package de.johannesbreitling.mealwhile.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "No valid unit provided")
public class IllegalUnitException extends RuntimeException {

    public IllegalUnitException() {
        super();
    }

}
