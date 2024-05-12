package com.boosters.initial.assessment.SecureService.api.controller;

import com.boosters.initial.assessment.SecureService.api.exception.SecureServiceException;
import com.boosters.initial.assessment.SecureService.api.model.dto.UserDTO;
import com.boosters.initial.assessment.SecureService.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for User related operations.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * Create a new user.
     *
     * @param userDTO the user to create
     * @return the created user
     * @throws SecureServiceException if there is an error during creation
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws SecureServiceException {
        UserDTO createdUser = userService.saveUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Get a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the retrieved user
     * @throws SecureServiceException if there is an error during retrieval
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") long id) throws SecureServiceException {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Get a user by their username.
     *
     * @param username the username of the user to retrieve
     * @return the retrieved user
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
        UserDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    /**
     * Update an existing user.
     *
     * @param userDTO the user to update
     * @return the updated user
     * @throws SecureServiceException if there is an error during update
     */
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws SecureServiceException {
        UserDTO updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return a response entity with no content
     * @throws SecureServiceException if there is an error during deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) throws SecureServiceException {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Get all users.
     *
     * @return a list of all users
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}