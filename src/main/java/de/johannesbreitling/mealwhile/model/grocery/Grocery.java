package de.johannesbreitling.mealwhile.model.grocery;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;

@Table(name = "groceries")
@Getter
@RequiredArgsConstructor
@Entity
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grocery grocery = (Grocery) o;
        return Objects.equals(name, grocery.name);
    }

}
