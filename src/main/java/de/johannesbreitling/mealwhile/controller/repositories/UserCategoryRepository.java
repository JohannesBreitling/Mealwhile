package de.johannesbreitling.mealwhile.controller.repositories;

import de.johannesbreitling.mealwhile.model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {

    UserCategory findByName(String name);
    UserCategory findById(String id);

}
