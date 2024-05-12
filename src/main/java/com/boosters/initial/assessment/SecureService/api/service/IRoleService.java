package com.boosters.initial.assessment.SecureService.api.service;


import com.boosters.initial.assessment.SecureService.api.exception.SecureServiceException;
import com.boosters.initial.assessment.SecureService.api.model.dto.RoleDTO;

import java.util.List;

/**
 * The IRoleService interface.
 */
public interface IRoleService {

    /**
     * Save role.
     *
     * @param roleDTO the role DTO
     * @return the role DTO
     * @throws SecureServiceException the secure service exception
     */
    RoleDTO saveRole(RoleDTO roleDTO) throws SecureServiceException;

    /**
     * Gets role by id.
     *
     * @param idRole the id role
     * @return the role by id
     * @throws SecureServiceException the secure service exception
     */
    RoleDTO getRoleById(long idRole) throws SecureServiceException;

    /**
     * Gets role by name.
     *
     * @param roleName the role name
     * @return the role by name
     * @throws SecureServiceException the secure service exception
     */
    RoleDTO getRoleByName(String roleName) throws SecureServiceException;

    /**
     * Update role.
     *
     * @param roleDTO the role DTO
     * @return the role DTO
     * @throws SecureServiceException the secure service exception
     */
    RoleDTO updateRole(RoleDTO roleDTO) throws SecureServiceException;

    /**
     * Delete role.
     *
     * @param idRole the id role
     * @throws SecureServiceException the secure service exception
     */
    void deleteRole(long idRole) throws SecureServiceException;

    /**
     * Gets all roles.
     *
     * @return the all roles
     */
    List<RoleDTO> getAllRoles();
}