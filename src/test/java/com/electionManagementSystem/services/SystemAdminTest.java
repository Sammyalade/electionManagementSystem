package com.electionManagementSystem.services;

import com.system.ElectionManagement.repositories.ElectionRepository;
import com.system.ElectionManagement.services.AdminServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SystemAdminTest {
    @Autowired
    private AdminServices adminServices;
    @Autowired
    private ElectionRepository electionRepository;
    @Test
    void testAdminCanSheduleElection() {
    }

}
