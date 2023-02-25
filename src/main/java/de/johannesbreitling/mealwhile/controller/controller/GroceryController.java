package de.johannesbreitling.mealwhile.controller.controller;

import de.johannesbreitling.mealwhile.controller.exceptions.EntityAlreadyExistsException;
import de.johannesbreitling.mealwhile.controller.exceptions.IllegalGroceryFlagException;
import de.johannesbreitling.mealwhile.controller.services.GroceryService;
import de.johannesbreitling.mealwhile.controller.utils.converter.GroceryConverter;
import de.johannesbreitling.mealwhile.model.grocery.Grocery;
import de.johannesbreitling.mealwhile.model.grocery.GroceryFlag;
import de.johannesbreitling.mealwhile.model.requests.GroceryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            Grocery existingGrocery = groceryService.getGroceryByName(request.name());

            if (existingGrocery != null) {
                throw new EntityAlreadyExistsException();
            }

            flags = GroceryConverter.convertFlagsFromArray(request.flags());
            Grocery newGrocery = new Grocery(request.name(), flags);
            this.groceryService.saveGrocery(newGrocery);
        } catch (IllegalArgumentException e) {
            throw new IllegalGroceryFlagException();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Grocery>> getGroceries() {
        List<Grocery> groceries = groceryService.getGroceries();
        return new ResponseEntity<>(groceries, HttpStatus.OK);
    }

}
