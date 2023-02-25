package de.johannesbreitling.mealwhile.model.recipe;

import de.johannesbreitling.mealwhile.model.grocery.Grocery;
import de.johannesbreitling.mealwhile.model.user.UserCategory;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Recipe {

    @Id
    @GenericGenerator(name = "entity_id", strategy = "de.johannesbreitling.mealwhile.controller.utils.EntityIdGenerator")
    @GeneratedValue(generator = "entity_id")
    private String id;

    private String name;

    @ManyToMany
    List<Ingredient> ingredients = new ArrayList<>();

    private String notes;
    private String instructions;

    @ManyToOne
    private UserCategory userCategory;

    public Recipe(String name, String notes, String instructions, UserCategory userCategory) {
        this.name = name;
        this.notes = notes;
        this.instructions = instructions;
        this.userCategory = userCategory;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeGrocery(Grocery grocery) {
        ingredients.removeIf(ingredient -> ingredient.getGrocery().equals(grocery));
    }

    public boolean containsGrocery(Grocery grocery) {
        for (Ingredient ingredient: ingredients) {
            if (ingredient.getGrocery().equals(grocery)) {
                return true;
            }
        }

        return false;
    }

}
