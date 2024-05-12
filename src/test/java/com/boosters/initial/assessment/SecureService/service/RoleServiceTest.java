package com.boosters.initial.assessment.SecureService.service;

import com.boosters.initial.assessment.SecureService.api.enums.RoleEnum;
import com.boosters.initial.assessment.SecureService.api.exception.SecureServiceException;
import com.boosters.initial.assessment.SecureService.api.model.dto.RoleDTO;
import com.boosters.initial.assessment.SecureService.api.service.IRoleService;
import com.boosters.initial.assessment.SecureService.config.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class RoleServiceTest extends AbstractTest {

    /** The role service. */
    @Autowired
    private IRoleService roleService;

    /**
     * Test save role.
     *
     * @throws SecureServiceException the secure service exception
     */
    @Test
    void testSaveAndDeleteRole() throws SecureServiceException {
        RoleDTO role = RoleDTO.builder()
                .role(RoleEnum.TESTER)
                .build();

        RoleDTO savedRole = roleService.saveRole(role);

        assertNotNull(savedRole);
        log.info("Role saved: {}", savedRole);

        roleService.deleteRole(savedRole.getIdRole());
        log.info("Role deleted");
    }

    /**
     * Test save role with null.
     */
    @Test
    void testSaveRoleWithNull() {
        RoleDTO role = RoleDTO.builder()
                .role(null)
                .build();

        assertThrows(SecureServiceException.class, () -> roleService.saveRole(role));
    }

    @Test
    void testSaveRoleWhenRoleAlreadyExists() {
        RoleDTO role = RoleDTO.builder()
                .role(RoleEnum.ADMIN)
                .build();

        assertThrows(SecureServiceException.class, () -> roleService.saveRole(role));
    }

    /**
     * Test get role by id.
     */
    @Test
    void testGetRoleById() throws SecureServiceException {
        RoleDTO role = roleService.getRoleById(1L);
        assertNotNull(role);
    }

    /**
     * Test get role by id when role does not exist.
     */
    @Test
    void testGetRoleByIdWhenRoleDoesNotExist() {
        assertThrows(SecureServiceException.class, () ->
                roleService.getRoleById(100L));
    }

    /**
     * Test get role by name.
     */
    @Test
    void testGetRoleByName() throws SecureServiceException {
        RoleDTO role = roleService.getRoleByName("ADMIN");
        assertNotNull(role);
        log.info("Role found: {}", role);
    }

    /**
     * Test get role by name when role does not exist.
     */
    @Test
    void testGetRoleByNameWhenRoleDoesNotExist() {
        assertThrows(SecureServiceException.class, () ->
                roleService.getRoleByName("RoleNotExist"));
    }

    /**
     * Test update role.
     */
    @Test
    void testUpdateRole() throws SecureServiceException {
        RoleDTO role = RoleDTO.builder()
                .idRole(2L)
                .role(RoleEnum.TESTER)
                .build();

        RoleDTO updatedRole = roleService.updateRole(role);
        assertNotNull(updatedRole);
        log.info("Role updated: {}", updatedRole);

        role = RoleDTO.builder()
                .idRole(2L)
                .role(RoleEnum.USER)
                .build();

        updatedRole = roleService.updateRole(role);
        assertNotNull(updatedRole);
        log.info("Role updated: {}", updatedRole);
    }

    /**
     * Test update role with id role null.
     */
    @Test
    void testUpdateRoleWithIdRoleNull() {
        RoleDTO role = RoleDTO.builder()
                .idRole(null)
                .role(RoleEnum.TESTER)
                .build();

        assertThrows(SecureServiceException.class, () -> roleService.updateRole(role));
    }

    @Test
    void testUpdateRoleWithNull() {
        RoleDTO role = RoleDTO.builder()
                .idRole(2L)
                .role(null)
                .build();

        assertThrows(SecureServiceException.class, () -> roleService.updateRole(role));
    }

    /**
     * Test update role when role does not exist.
     */
    @Test
    void testUpdateRoleWhenRoleDoesNotExist() {
        RoleDTO role = RoleDTO.builder()
                .idRole(100L)
                .role(RoleEnum.TESTER)
                .build();

        assertThrows(SecureServiceException.class, () -> roleService.updateRole(role));
    }

    /**
     * Test update role when role already exists.
     */
    @Test
    void testUpdateRoleWhenRoleAlreadyExists() {
        RoleDTO role = RoleDTO.builder()
                .idRole(2L)
                .role(RoleEnum.ADMIN)
                .build();

        assertThrows(SecureServiceException.class, () -> roleService.updateRole(role));
    }

    /**
     * Test delete role when role does not exist.
     */
    @Test
    void testDeleteRoleWhenRoleDoesNotExist() {
        assertThrows(SecureServiceException.class, () -> roleService.deleteRole(100L));
    }

    /**
     * Test get all roles.
     */
    @Test
    public void testGetAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        assertNotNull(roles);

        roles.forEach(role -> log.info("Role: {}", role));
    }
}