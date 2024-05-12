package com.boosters.initial.assessment.SecureService.controller;

import com.boosters.initial.assessment.SecureService.api.model.dto.UserDTO;
import com.boosters.initial.assessment.SecureService.config.AbstractTest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Test class for UserController.
 */
public class UserControllerTest extends AbstractTest {

    /** The mock mvc. */
    private MockMvc mockMvc;

    /** The object mapper. */
    private ObjectMapper objectMapper;

    /**
     * Sets the up.
     *
     * @param webApplicationContext the web application context
     */
    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Test create and delete user.
     *
     * @throws Exception the exception
     */
    @Test
    void testCreateAndDeleteUser() throws Exception {

        UserDTO userDTO = UserDTO.builder()
                .username("testUser")
                .password("testPassword")
                .role("USER")
                .build();

        MvcResult result = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());

        result = mockMvc.perform(delete("/api/users/4")).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    /**
     * Test create user with null.
     *
     * @throws Exception the exception
     */
    @Test
    void testCreateUserWithNull() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .username(null)
                .password(null)
                .role(null)
                .build();

        MvcResult result = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andReturn();

        assertEquals(424, result.getResponse().getStatus());
    }

    /**
     * Test create user when username exists.
     *
     * @throws Exception the exception
     */
    @Test
    void testCreateUserWhenUsernameExists() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .username("User")
                .password("Test123")
                .role("USER")
                .build();

        MvcResult result = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andReturn();

        assertEquals(409, result.getResponse().getStatus());
    }

    /**
     * Test create user when role does not exist.
     *
     * @throws Exception the exception
     */
    @Test
    void testCreateUserWhenRoleDoesNotExist() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .username("TestUser")
                .password("Test123")
                .role("NOT_EXISTING_ROLE")
                .build();

        MvcResult result = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andReturn();

        assertEquals(409, result.getResponse().getStatus());
    }

    /**
     * Test create user with null username.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetUserById() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/users/1")).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    /**
     * Test get user by id when user does not exist.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetUserByIdWhenUserDoesNotExist() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/users/100")).andReturn();
        assertEquals(404, result.getResponse().getStatus());
    }

    /**
     * Test get user by username.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetUserByUsername() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/users/username/User")).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    /**
     * Test get user by username when user does not exist.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetUserByUsernameWhenUserDoesNotExist() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/users/username/NonExistingUser")).andReturn();
        assertEquals(404, result.getResponse().getStatus());
    }

    /**
     * Test update user.
     *
     * @throws Exception the exception
     */
    @Test
    void testUpdateUser() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .idUser(2L)
                .username("User")
                .password("NewPassword123")
                .role("USER")
                .build();

        MvcResult result = mockMvc.perform(put("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    /**
     * Test update user when user does not exist.
     *
     * @throws Exception the exception
     */
    @Test
    void testUpdateUserWhenUserDoesNotExist() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .idUser(100L)
                .username("User")
                .password("NewPassword123")
                .role("USER")
                .build();

        MvcResult result = mockMvc.perform(put("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }

    /**
     * Test update user with null.
     *
     * @throws Exception the exception
     */
    @Test
    void testUpdateUserWithNull() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .idUser(2L)
                .username(null)
                .password(null)
                .role(null)
                .build();

        MvcResult result = mockMvc.perform(put("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andReturn();

        assertEquals(424, result.getResponse().getStatus());
    }

    /**
     * Test update user when user id is null.
     *
     * @throws Exception the exception
     */
    @Test
    void testUpdateUserWhenUserIdIsNull() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .username("User")
                .password("NewPassword123")
                .role("USER")
                .build();

        MvcResult result = mockMvc.perform(put("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andReturn();

        assertEquals(424, result.getResponse().getStatus());
    }

    /**
     * Test update user when username exists.
     *
     * @throws Exception the exception
     */
    @Test
    void testUpdateUserWhenUsernameExists() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .idUser(2L)
                .username("Admin")
                .password("NewPassword123")
                .role("USER")
                .build();

        MvcResult result = mockMvc.perform(put("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andReturn();

        assertEquals(409, result.getResponse().getStatus());
    }

    /**
     * Test update user when role does not exist.
     *
     * @throws Exception the exception
     */
    @Test
    void testUpdateUserWhenRoleDoesNotExist() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .idUser(2L)
                .username("User")
                .password("NewPassword123")
                .role("NOT_EXISTING_ROLE")
                .build();

        MvcResult result = mockMvc.perform(put("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }

    /**
     * Test delete user.
     *
     * @throws Exception the exception
     */
    @Test
    void testDeleteUserWhenUserDoesNotExist() throws Exception {
        MvcResult result = mockMvc.perform(delete("/api/users/100")).andReturn();
        assertEquals(404, result.getResponse().getStatus());
    }

    /**
     * Test get all users.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetAllUsers() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/users")).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

}