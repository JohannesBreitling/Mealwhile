package de.johannesbreitling.mealwhile.controller.controller;

import de.johannesbreitling.mealwhile.controller.exceptions.IllegalGroceryFlagException;
import de.johannesbreitling.mealwhile.controller.services.GroceryService;
import de.johannesbreitling.mealwhile.controller.utils.converter.GroceryConverter;
import de.johannesbreitling.mealwhile.model.Grocery;
import de.johannesbreitling.mealwhile.model.GroceryFlag;
import de.johannesbreitling.mealwhile.model.requests.GroceryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/groceries")
public class GroceryController {

    private final GroceryService groceryService;

    @Autowired
    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @PostMapping("/")
    public void createGrocery(@RequestBody GroceryRequest request) {

        List<GroceryFlag> flags;

        try {
            flags = GroceryConverter.convertFlagsFromArray(request.flags());
            Grocery newGrocery = new Grocery(request.name(), flags);
            this.groceryService.saveGrocery(newGrocery);
        } catch (IllegalArgumentException e) {
            throw new IllegalGroceryFlagException();
        }

    }

}
