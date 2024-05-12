package com.boosters.initial.assessment.SecureService.api.service.impl;

import com.boosters.initial.assessment.SecureService.api.enums.RoleEnum;
import com.boosters.initial.assessment.SecureService.api.exception.RequiredFieldsException;
import com.boosters.initial.assessment.SecureService.api.exception.RoleAlreadyExistsException;
import com.boosters.initial.assessment.SecureService.api.exception.RoleNotExistException;
import com.boosters.initial.assessment.SecureService.api.exception.SecureServiceException;
import com.boosters.initial.assessment.SecureService.api.model.dto.RoleDTO;
import com.boosters.initial.assessment.SecureService.api.model.entity.RoleEntity;
import com.boosters.initial.assessment.SecureService.api.repository.RoleRepository;
import com.boosters.initial.assessment.SecureService.api.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The RoleServiceImpl class.
 */
@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    /** The role repository. */
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) throws SecureServiceException {

        log.info("Saving role: {}", roleDTO);

        validateDTOBeforeUpdate(roleDTO, false);
        validateRoleExists(roleDTO.getRole());

        RoleEntity roleEntity = convertToEntity(roleDTO);
        roleEntity = roleRepository.save(roleEntity);

        log.info("Role saved: {}", roleEntity);

        return convertToDTO(roleEntity);
    }

    @Override
    public RoleDTO getRoleById(long idRole) throws SecureServiceException {

        log.info("Getting role by id: {}", idRole);

        return roleRepository.findById(idRole)
                .map(this::convertToDTO)
                .orElseThrow(
                        () -> {
                            log.error("Role with id '{}' does not exist", idRole);
                            return new RoleNotExistException(String.valueOf(idRole));
                        }
                );
    }

    @Override
    public RoleDTO getRoleByName(String roleName) throws SecureServiceException {

        log.info("Getting role by name: {}", roleName);

        RoleEnum role = RoleEnum.getRoleEnum(roleName);
        RoleEntity roleEntity =  roleRepository.findByRole(role);

        log.info("Role found: {}", roleEntity);

        return convertToDTO(roleEntity);
    }

    @Override
    public RoleDTO updateRole(RoleDTO roleDTO) throws SecureServiceException {

        log.info("Updating role: {}", roleDTO);

        validateDTOBeforeUpdate(roleDTO, true);
        RoleDTO roleToUpdate = getRoleById(roleDTO.getIdRole());

        if (!roleToUpdate.getRole().equals(roleDTO.getRole())) {
            validateRoleExists(roleDTO.getRole());
        }

        RoleEntity roleEntity = convertToEntity(roleDTO);
        roleEntity.setIdRole(roleToUpdate.getIdRole());
        roleEntity = roleRepository.save(roleEntity);

        log.info("Role updated: {}", roleEntity);

        return convertToDTO(roleEntity);
    }

    @Override
    public void deleteRole(long idRole) throws SecureServiceException {
        log.info("Deleting role by id: {}", idRole);

        getRoleById(idRole);
        roleRepository.deleteById(idRole);

        log.info("Role deleted");
    }

    @Override
    public List<RoleDTO> getAllRoles() {

        log.info("Getting all roles");

        return roleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    /**
     * Convert RoleEntity to RoleDTO.
     *
     * @param roleEntity the roleEntity
     * @return the role dto
     */
    private RoleDTO convertToDTO(RoleEntity roleEntity) {
        return RoleDTO.builder()
                .idRole(roleEntity.getIdRole())
                .role(roleEntity.getRole())
                .build();
    }

    /**
     * Convert RoleDTO to RoleEntity.
     *
     * @param roleDTO the roleDTO
     * @return the role entity
     */
    private RoleEntity convertToEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(roleDTO.getRole());

        return roleEntity;
    }

    /**
     * Validate DTO before update.
     *
     * @param roleDTO the role DTO
     * @param validateId validate id is required
     * @throws RequiredFieldsException the required fields exception
     */
    private void validateDTOBeforeUpdate(RoleDTO roleDTO, boolean validateId) throws RequiredFieldsException {

        if (validateId) {
            validateRoleIdNotNull(roleDTO.getIdRole());
        }

        if (null == roleDTO.getRole()) {
            log.error("Role is required");
            throw new RequiredFieldsException();
        }
    }

    /**
     * Validate role id not null.
     *
     * @param idRole the id role
     * @throws RequiredFieldsException the required fields exception
     */
    private void validateRoleIdNotNull(Long idRole) throws RequiredFieldsException {
        if (null == idRole) {
            log.error("Role id is required");
            throw new RequiredFieldsException();
        }
    }

    /**
     * Validate role exists.
     *
     * @param role the role
     * @throws RoleAlreadyExistsException the role already exists exception
     */
    private void validateRoleExists(RoleEnum role) throws RoleAlreadyExistsException {
        if (null != roleRepository.findByRole(role)) {
            log.error("Role '{}' already exists", role);
            throw new RoleAlreadyExistsException(role.name());
        }
    }
}