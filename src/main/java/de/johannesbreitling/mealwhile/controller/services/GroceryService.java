package de.johannesbreitling.mealwhile.controller.services;

import de.johannesbreitling.mealwhile.controller.repositories.GroceryRepository;
import de.johannesbreitling.mealwhile.model.Grocery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroceryService {

    private GroceryRepository groceryRepository;

    @Autowired
    public GroceryService(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }

    public void saveGrocery(Grocery grocery) {
        this.groceryRepository.save(grocery);
    }

}
