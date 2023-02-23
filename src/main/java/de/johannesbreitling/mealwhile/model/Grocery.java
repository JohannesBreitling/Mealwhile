package de.johannesbreitling.mealwhile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "groceries")
@Getter
@RequiredArgsConstructor
public class Grocery {

    public Grocery(String name, List<GroceryFlag> flags) {
        this.name = name;
        this.flags = flags;
    }

    @Id
    @GenericGenerator(name = "entity_id", strategy = "de.johannesbreitling.mealwhile.controller.utils.EntityIdGenerator")
    @GeneratedValue(generator = "entity_id")
    private String id;

    @Column(unique = true)
    private String name;

    @ElementCollection
    private List<GroceryFlag> flags;

}
