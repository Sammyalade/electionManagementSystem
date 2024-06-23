package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.AdminRequest;
import com.system.ElectionManagement.dtos.requests.VoterRequest;
import com.system.ElectionManagement.exceptions.AdminException;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.system.ElectionManagement.models.EligibilityStatus.ELIGIBLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;

    @Test
    void registerAsAdmin() {
        AdminRequest adminRequest = AdminRequest.builder()
                .username("admin")
                .password("gopass")
                .build();
        var adminResponse = adminService.registerAsAdmin(adminRequest);
        assertThat(adminResponse).isNotNull();
        assertThat(adminResponse.getUsername()).isEqualTo("admin");
        assertThat(adminResponse.getPassword()).isEqualTo("gopass");
    }
    @Test
    void registerAdminThatAlreadyExistsThrowsException() {
        registerAsAdmin();
        AdminRequest adminRequest = AdminRequest.builder()
                .username("admin")
                .password("gopass")
                .build();
        assertThatThrownBy(() -> adminService.registerAsAdmin(adminRequest))
                .isInstanceOf(AdminException.class)
                .hasMessageContaining("Username already exists");
    }
}