package com.boosters.initial.assessment.SecureService.api.service;

import com.boosters.initial.assessment.SecureService.api.exception.SecureServiceException;
import com.boosters.initial.assessment.SecureService.api.exception.UserNotExistException;
import com.boosters.initial.assessment.SecureService.api.model.dto.UserDTO;

import java.util.List;

/**
 * Interface UserService
 */
public interface IUserService {

    /**
     * Save user
     *
     * @param userDTO the user DTO
     * @return the user DTO
     * @throws SecureServiceException the secure service exception
     */
    public UserDTO saveUser(UserDTO userDTO) throws SecureServiceException;

    /**
     * Get user by id
     *
     * @param idUser the id user
     * @return the user by id
     * @throws SecureServiceException the secure service exception
     */
    public UserDTO getUserById(Long idUser) throws SecureServiceException;

    /**
     * Get user by username
     *
     * @param username the username
     * @return the user by username
     */
    public UserDTO getUserByUsername(String username);

    /**
     * Update user
     *
     * @param userDTO the user DTO
     * @return the user DTO
     * @throws SecureServiceException the secure service exception
     */
    public UserDTO updateUser(UserDTO userDTO) throws SecureServiceException;

    /**
     * Delete user
     *
     * @param idUser the id user
     * @throws SecureServiceException the secure service exception
     */
    public void deleteUser(Long idUser) throws SecureServiceException;

    /**
     * Get all users
     *
     * @return the list
     */
    public List<UserDTO> getAllUsers();
}
