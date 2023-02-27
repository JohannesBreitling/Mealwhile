package de.johannesbreitling.mealwhile.controller.services;

import de.johannesbreitling.mealwhile.controller.repositories.GroceryRepository;
import de.johannesbreitling.mealwhile.model.grocery.Grocery;
import de.johannesbreitling.mealwhile.model.recipe.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryService {

    private final GroceryRepository groceryRepository;

    @Autowired
    public GroceryService(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }

    public void saveGrocery(Grocery grocery) {
        this.groceryRepository.save(grocery);
    }

    public List<Grocery> getGroceries() {
        return groceryRepository.findAll();
    }

    public Grocery getGroceryByName(String name) {
        return groceryRepository.findByName(name);
    }

    public Grocery getGroceryById(String id) {
        return groceryRepository.findById(id);
    }

    public void deleteGrocery(Grocery grocery) {
        groceryRepository.delete(grocery);
    }

}
