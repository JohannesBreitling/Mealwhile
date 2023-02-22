package de.johannesbreitling.mealwhile.controller.repositories;

import de.johannesbreitling.mealwhile.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for groceries as the base for meals
 */
public interface GroceryRepository extends JpaRepository<Grocery, Long> {

    Grocery findByName(String name);
    Grocery findById(String id);

}
