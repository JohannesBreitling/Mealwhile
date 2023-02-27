package de.johannesbreitling.mealwhile.controller.controller;

import de.johannesbreitling.mealwhile.controller.exceptions.EntityAlreadyExistsException;
import de.johannesbreitling.mealwhile.controller.exceptions.EntityNotFoundException;
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
@SuppressWarnings("rawtypes")
@RequestMapping("/groceries")
public class GroceryController {

    private final GroceryService groceryService;

    @Autowired
    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @PostMapping("/")
    public ResponseEntity createGrocery(@RequestBody GroceryRequest request) {

        List<GroceryFlag> flags;

        try {
            Grocery existingGrocery = groceryService.getGroceryByName(request.name());

            if (existingGrocery != null) {
                throw new EntityAlreadyExistsException();
            }

            flags = GroceryConverter.convertFlagsFromArray(request.flags());
            Grocery newGrocery = new Grocery(request.name(), flags);
            this.groceryService.saveGrocery(newGrocery);

            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new IllegalGroceryFlagException();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity updateGrocery(@PathVariable String id, @RequestBody GroceryRequest grocery) {
        Grocery foundGrocery = groceryService.getGroceryById(id);

        if (grocery == null) {
            throw new EntityNotFoundException();
        }

        try {
            foundGrocery.setName(grocery.name());
            List<GroceryFlag> flags = GroceryConverter.convertFlagsFromArray(grocery.flags());
            foundGrocery.setFlags(flags);

            groceryService.saveGrocery(foundGrocery);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new IllegalGroceryFlagException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGrocery(@PathVariable String id) {
        Grocery grocery = groceryService.getGroceryById(id);

        if (grocery != null) {
            groceryService.deleteGrocery(grocery);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Grocery>> getGroceries() {
        List<Grocery> groceries = groceryService.getGroceries();
        return new ResponseEntity<>(groceries, HttpStatus.OK);
    }

}
