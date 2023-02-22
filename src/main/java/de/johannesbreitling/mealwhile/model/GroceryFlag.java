package de.johannesbreitling.mealwhile.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

public enum GroceryFlag {

    VEGAN,
    MEAT,
    GLUTEN,
    LACTOSE,
    NUT

}
