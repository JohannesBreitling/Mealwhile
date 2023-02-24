package de.johannesbreitling.mealwhile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_categories")
@Getter
@Setter
@NoArgsConstructor
public class UserCategory {

    @Id
    @GenericGenerator(name = "entity_id", strategy = "de.johannesbreitling.mealwhile.controller.utils.EntityIdGenerator")
    @GeneratedValue(generator = "entity_id")
    private String id;

    @Column(unique = true)
    private String name;

    private String color;

    public UserCategory(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "UserCategory < " + name + " >";
    }
}
