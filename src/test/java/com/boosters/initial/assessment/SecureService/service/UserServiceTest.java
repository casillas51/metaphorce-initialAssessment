package com.boosters.initial.assessment.SecureService.service;

import com.boosters.initial.assessment.SecureService.api.exception.SecureServiceException;
import com.boosters.initial.assessment.SecureService.api.model.dto.UserDTO;
import com.boosters.initial.assessment.SecureService.api.service.IUserService;
import com.boosters.initial.assessment.SecureService.config.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The UserServiceTest class.
 */
@Slf4j
public class UserServiceTest extends AbstractTest {

    /** The user service. */
    @Autowired
    private IUserService userService;

    /**
     * Test save user.
     *
     * @throws SecureServiceException the secure service exception
     */
    @Test
    public void testSaveUser() throws SecureServiceException {
        UserDTO user = UserDTO.builder()
                .username("Test")
                .password("Test123")
                .role("USER")
                .build();

        UserDTO savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        log.info("User saved: {}", savedUser);
    }

    /**
     * Test save user with null.
     */
    @Test
    void testSaveUserWithNull() {
        UserDTO user = UserDTO.builder()
                .username(null)
                .password(null)
                .role(null)
                .build();

        assertThrows(SecureServiceException.class, () -> userService.saveUser(user));
    }

    /**
     * Test save user when username exists.
     */
    @Test
    void testSaveUserWhenUsernameExists() {
        UserDTO user = UserDTO.builder()
                .username("User")
                .password("Test123")
                .role("USER")
                .build();

        assertThrows(SecureServiceException.class, () -> userService.saveUser(user));
    }

    /**
     * Test get user by id.
     *
     * @throws SecureServiceException the secure service exception
     */
    @Test
    void testGetUserById() throws SecureServiceException {
        UserDTO user = userService.getUserById(1L);
        assertNotNull(user);
        log.info("User found: {}", user);
    }

    /**
     * Test get user by id when user does not exist.
     */
    @Test
    void testGetUserByIdWhenUserDoesNotExist() {
        assertThrows(SecureServiceException.class, () -> userService.getUserById(100L));
    }

    /**
     * Test get user by username.
     */
    @Test
    void testGetUserByUsername() {
        UserDTO user = userService.getUserByUsername("User");
        assertNotNull(user);
        log.info("User by username found: {}", user);
    }

    /**
     * Test get user by username when user does not exist.
     */
    @Test
    void testGetUserByUsernameWhenUserDoesNotExist() {
        UserDTO user = userService.getUserByUsername("UserNotExist");
        assertNull(user);
    }

    /**
     * Test update user.
     *
     * @throws SecureServiceException the secure service exception
     */
    @Test
    void testUpdateUser() throws SecureServiceException {
        UserDTO user = UserDTO.builder()
                .idUser(2L)
                .username("User")
                .password("TestNewPassword123")
                .role("USER")
                .build();

        UserDTO updatedUser = userService.updateUser(user);
        assertNotNull(updatedUser);
        log.info("User updated: {}", updatedUser);
    }

    /**
     * Test update user with id user null.
     */
    @Test
    void testUpdateUserWhitIdUserNull() {
        UserDTO user = UserDTO.builder()
                .idUser(null)
                .username("User")
                .password("TestNewPassword123")
                .role("USER")
                .build();

        assertThrows(SecureServiceException.class, () -> userService.updateUser(user));
    }

    /**
     * Test update user with id user negative.
     */
    @Test
    void testUpdateUserWithNull() {
        UserDTO user = UserDTO.builder()
                .idUser(2L)
                .username(null)
                .password(null)
                .role(null)
                .build();

        assertThrows(SecureServiceException.class, () -> userService.updateUser(user));
    }

    /**
     * Test update user when user does not exist.
     */
    @Test
    void testUpdateUserWhenUserDoesNotExist() {
        UserDTO user = UserDTO.builder()
                .idUser(100L)
                .username("User")
                .password("TestNewPassword123")
                .role("USER")
                .build();

        assertThrows(SecureServiceException.class, () -> userService.updateUser(user));
    }

    /**
     * Test update user when username exists.
     */
    @Test
    void testUpdateUserWhenUsernameExists() {
        UserDTO user = UserDTO.builder()
                .idUser(2L)
                .username("Admin")
                .password("TestNewPassword123")
                .role("ADMIN")
                .build();

        assertThrows(SecureServiceException.class, () -> userService.updateUser(user));
    }

    /**
     * Test delete user.
     *
     * @throws SecureServiceException the secure service exception
     */
    @Test
    void testDeleteUser() throws SecureServiceException {
        userService.deleteUser(3L);
        assertTrue(true);
    }

    /**
     * Test delete user when user does not exist.
     */
    @Test
    void testDeleteUserWhenUserDoesNotExist() {
        assertThrows(SecureServiceException.class, () -> userService.deleteUser(100L));
    }

    /**
     * Test get all users.
     */
    @Test
    public void testGetAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        assertNotNull(users);

        users.forEach(user -> log.info("User: {}", user));
    }
}
