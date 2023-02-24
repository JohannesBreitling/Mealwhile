package de.johannesbreitling.mealwhile.model;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@RequiredArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GenericGenerator(name = "entity_id", strategy = "de.johannesbreitling.mealwhile.controller.utils.EntityIdGenerator")
    @GeneratedValue(generator = "entity_id")
    private String id;

    @Column(unique = true)
    private String name;

    private String passwordHash;

    @ManyToOne
    private UserCategory category;

    public User(String name, String passwordHash, UserCategory category) {
        this.name = name;
        this.passwordHash = passwordHash;
        this.category = category;
    }

    @Override
    public String toString() {
        return "User < " + name + " : " + category + " >";
    }

}
