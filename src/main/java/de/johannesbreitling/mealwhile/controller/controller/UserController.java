package de.johannesbreitling.mealwhile.controller.controller;

import de.johannesbreitling.mealwhile.controller.exceptions.EntityAlreadyExistsException;
import de.johannesbreitling.mealwhile.controller.services.UserService;
import de.johannesbreitling.mealwhile.model.User;
import de.johannesbreitling.mealwhile.model.UserCategory;
import de.johannesbreitling.mealwhile.model.requests.UserCategoryRequest;
import de.johannesbreitling.mealwhile.model.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    @SuppressWarnings("rawtypes")
    public ResponseEntity createUser(@RequestBody UserRequest user) {

        User newUser = new User(user.name(), user.password(), user.category());

        System.out.println(newUser);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/categories/")
    @SuppressWarnings("rawtypes")
    public ResponseEntity createUserCategory(@RequestBody UserCategoryRequest category) {

        if (userService.getUserCategoryByName(category.name()) != null) {
            // Category already saved
            throw new EntityAlreadyExistsException();
        }

        // Create new category and save to repository
        UserCategory newCategory = new UserCategory(category.name(), category.color());
        userService.saveUserCategory(newCategory);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/categories/")
    public ResponseEntity<List<UserCategory>> getUserCategories() {
        List<UserCategory> categories = userService.getUserCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
