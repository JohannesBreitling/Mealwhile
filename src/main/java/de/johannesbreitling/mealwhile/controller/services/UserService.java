package de.johannesbreitling.mealwhile.controller.services;

import de.johannesbreitling.mealwhile.controller.repositories.UserCategoryRepository;
import de.johannesbreitling.mealwhile.controller.repositories.UserRepository;
import de.johannesbreitling.mealwhile.model.User;
import de.johannesbreitling.mealwhile.model.UserCategory;
import de.johannesbreitling.mealwhile.model.requests.UserCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserCategoryRepository userCategoryRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserCategoryRepository userCategoryRepository) {
        this.userRepository = userRepository;
        this.userCategoryRepository = userCategoryRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User getUserById(String id) {
        return userRepository.findById(id);
    }

    public List<User> getUsersByCategory(UserCategory category) {
        return userRepository.findByCategory(category);
    }

    public void saveUserCategory(UserCategory category) {
        userCategoryRepository.save(category);
    }

    public void deleteUserCategory(UserCategory category) {
        userCategoryRepository.delete(category);
    }

    public void updateUserCategory(UserCategory category, UserCategoryRequest newCategory) {
        category.setColor(newCategory.color());
        category.setName(newCategory.name());

        userCategoryRepository.save(category);
    }

    public List<UserCategory> getUserCategories() {
        return userCategoryRepository.findAll();
    }

    public UserCategory getUserCategoryByName(String name) {
        return userCategoryRepository.findByName(name);
    }

    public UserCategory getUserCategoryById(String id) {
        return userCategoryRepository.findById(id);
    }

}
