package de.johannesbreitling.mealwhile.controller.controller;

import de.johannesbreitling.mealwhile.controller.exceptions.EntityAlreadyExistsException;
import de.johannesbreitling.mealwhile.controller.exceptions.EntityNotFoundException;
import de.johannesbreitling.mealwhile.controller.services.UserService;
import de.johannesbreitling.mealwhile.model.user.User;
import de.johannesbreitling.mealwhile.model.user.UserCategory;
import de.johannesbreitling.mealwhile.model.requests.UserCategoryRequest;
import de.johannesbreitling.mealwhile.model.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UserRequest user) {

        UserCategory category = userService.getUserCategoryById(user.categoryId());

        if (category == null) {
            throw new EntityNotFoundException();
        }

        User newUser = new User(user.name(), user.password(), category);
        System.out.println(newUser);
        userService.saveUser(newUser);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id,
                                     @RequestBody UserRequest user) {
        User foundUser = userService.getUserById(id);
        UserCategory foundCategory = userService.getUserCategoryById(user.categoryId());
        if (foundUser == null || foundCategory == null) {
            throw new EntityNotFoundException();
        }

        User newUser = new User(user.name(), user.password(), foundCategory);

        userService.updateUser(foundUser, newUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        User user = userService.getUserById(id);

        if (user != null) {
            userService.deleteUser(user);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/categories/")
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

    @PatchMapping("/categories/{id}")
    public ResponseEntity updateUserCategory(@PathVariable String id,
                                             @RequestBody UserCategoryRequest category) {
        UserCategory foundCategory = userService.getUserCategoryById(id);

        if (foundCategory == null) {
            throw new EntityNotFoundException();
        }

        userService.updateUserCategory(foundCategory, category);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity deleteUserCategory(@PathVariable String id) {
        UserCategory category = userService.getUserCategoryById(id);

        if (category != null) {
            userService.deleteUserCategory(category);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/categories/")
    public ResponseEntity<List<UserCategory>> getUserCategories() {
        List<UserCategory> categories = userService.getUserCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
