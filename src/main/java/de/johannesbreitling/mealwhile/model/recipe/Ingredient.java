package de.johannesbreitling.mealwhile.model.recipe;

import de.johannesbreitling.mealwhile.model.grocery.Grocery;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@RequiredArgsConstructor
@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GenericGenerator(name = "entity_id", strategy = "de.johannesbreitling.mealwhile.controller.utils.EntityIdGenerator")
    @GeneratedValue(generator = "entity_id")
    private String id;

    @ManyToOne
    private Grocery grocery;

    private float quantity;
    private IngredientUnit unit;

}