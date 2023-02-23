package de.johannesbreitling.mealwhile.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {

    @Id
    @GenericGenerator(name = "entity_id", strategy = "de.johannesbreitling.mealwhile.controller.utils.EntityIdGenerator")
    @GeneratedValue(generator = "entity_id")
    private String id;

    @Column(unique = true)
    private String name;

    private String password;

    @Embedded
    private UserCategory category;

}
