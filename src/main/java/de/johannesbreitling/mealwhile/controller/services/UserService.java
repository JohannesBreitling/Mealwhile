package de.johannesbreitling.mealwhile.controller.services;

import de.johannesbreitling.mealwhile.controller.repositories.UserCategoryRepository;
import de.johannesbreitling.mealwhile.controller.repositories.UserRepository;
import de.johannesbreitling.mealwhile.model.User;
import de.johannesbreitling.mealwhile.model.UserCategory;

public class UserService {

    private UserRepository userRepository;
    private UserCategoryRepository userCategoryRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void saveUserCategory(UserCategory category) {
        userCategoryRepository.save(category);
    }



}
