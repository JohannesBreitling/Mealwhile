package de.johannesbreitling.mealwhile.controller.repositories;

import de.johannesbreitling.mealwhile.model.User;
import de.johannesbreitling.mealwhile.model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByCategory(UserCategory category);
    User findByName(String name);
    User findById(String id);

}
