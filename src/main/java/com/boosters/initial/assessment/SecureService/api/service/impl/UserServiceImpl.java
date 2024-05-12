package com.boosters.initial.assessment.SecureService.api.service.impl;

import static com.boosters.initial.assessment.SecureService.api.util.ValidationUtils.validation;

import com.boosters.initial.assessment.SecureService.api.enums.RoleEnum;
import com.boosters.initial.assessment.SecureService.api.exception.*;
import com.boosters.initial.assessment.SecureService.api.model.dto.UserDTO;
import com.boosters.initial.assessment.SecureService.api.model.entity.RoleEntity;
import com.boosters.initial.assessment.SecureService.api.model.entity.UserEntity;
import com.boosters.initial.assessment.SecureService.api.repository.RoleRepository;
import com.boosters.initial.assessment.SecureService.api.repository.UserRepository;
import com.boosters.initial.assessment.SecureService.api.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class UserServiceImpl.
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    /** The role repository. */
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDTO saveUser(UserDTO userDTO) throws SecureServiceException {

        log.info("Saving user: {}", userDTO);

        validateDTOBeforeUpdate(userDTO, false);
        validateUsernameExists(userDTO.getUsername());

        UserEntity userEntity = convertToEntity(userDTO);
        userEntity = userRepository.save(userEntity);

        log.info("User saved: {}", userEntity);

        return convertToDTO(userEntity);
    }

    @Override
    public UserDTO getUserById(long idUser) throws SecureServiceException {

        log.info("Getting user by id: {}", idUser);

        return userRepository.findById(idUser)
                .map(this::convertToDTO)
                .orElseThrow(
                        () -> {
                            log.error("User with id '{}' does not exist", idUser);
                            return new UserNotExistException(idUser);
                        }
                );
    }

    @Override
    public UserDTO getUserByUsername(String username) {

        log.info("Getting user by username: {}", username);

        return userRepository.findByUsernameIgnoreCase(username)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws SecureServiceException {

        log.info("Updating user: {}", userDTO);

        validateDTOBeforeUpdate(userDTO, true);
        UserDTO userToUpdate = getUserById(userDTO.getIdUser());

        if(!userToUpdate.getUsername().equalsIgnoreCase(userDTO.getUsername())) {
            validateUsernameExists(userDTO.getUsername());
        }

        UserEntity userEntity = convertToEntity(userDTO);
        userEntity.setIdUser(userToUpdate.getIdUser());
        userEntity = userRepository.save(userEntity);

        log.info("User updated: {}", userEntity);

        return convertToDTO(userEntity);
    }

    @Override
    public void deleteUser(long idUser) throws SecureServiceException {
        log.info("Deleting user by id: {}", idUser);

        getUserById(idUser);
        userRepository.deleteById(idUser);

        log.info("User deleted");
    }

    @Override
    public List<UserDTO> getAllUsers() {

        log.info("Getting all users");

        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    /**
     * Convert to DTO.
     *
     * @param userEntity the user entity
     * @return the user DTO
     */
    private UserDTO convertToDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .idUser(userEntity.getIdUser())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .role(userEntity.getRole().getRole().name())
                .build();
    }

    /**
     * Convert to entity.
     *
     * @param userDTO the user DTO
     * @return the user entity
     * @throws RoleNotExistException the role not exist exception
     */
    private UserEntity convertToEntity(UserDTO userDTO) throws RoleNotExistException {

        RoleEnum role = RoleEnum.getRoleEnum(userDTO.getRole());
        RoleEntity roleEntity = roleRepository.findByRole(role);

        return new UserEntity(userDTO.getIdUser(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                roleEntity);
    }

    /**
     * Validate DTO before update.
     *
     * @param userDTO the user DTO
     * @param validateId validate id is required
     * @throws RequiredFieldsException the required fields exception
     */
    private void validateDTOBeforeUpdate(UserDTO userDTO, boolean validateId) throws RequiredFieldsException {

        if (validateId) {
            validateUserIdNotNull(userDTO.getIdUser());
        }

        if (validation().containsNull(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole())) {
            log.error("Missing required fields");
            throw new RequiredFieldsException();
        }
    }

    /**
     * Validate user id not null.
     *
     * @param idUser the id user
     * @throws RequiredFieldsException the required fields exception
     */
    private void validateUserIdNotNull(Long idUser) throws RequiredFieldsException {
        if (null == idUser) {
            log.error("User id is required");
            throw new RequiredFieldsException();
        }
    }

    /**
     * Validate username exists.
     *
     * @param username the username
     * @throws UsernameAlreadyExistsException the username already exists exception
     */
    private void validateUsernameExists(String username) throws UsernameAlreadyExistsException {
        if (null != getUserByUsername(username)) {
            log.error("User '{}' already exists", username);
            throw new UsernameAlreadyExistsException(username);
        }
    }

}
