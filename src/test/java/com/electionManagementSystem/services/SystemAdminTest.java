package com.electionManagementSystem.services;


import com.electionManagementSystem.dtos.requests.AdminRequest;
import com.electionManagementSystem.dtos.requests.ElectionRequest;
import com.electionManagementSystem.dtos.responses.AdminResponse;
import com.electionManagementSystem.dtos.responses.ScheduleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SystemAdminTest {
    @Autowired
    private AdminServices adminService;

    @Test
    void testAdminCanSheduleElection(){
        AdminRequest admin = new AdminRequest();
        admin.setUsername("admin101");
        admin.setPassword("password");
        AdminResponse adminResponse = adminService.createAdmin(admin);
        ElectionRequest electionRequest = new ElectionRequest();
        electionRequest.setStartDate("31-12-2024");
        electionRequest.setEndDate("31-12-2024");
        electionRequest.setStartTime("06:00:00");
        electionRequest.setEndTime("18:00:00");
        electionRequest.setCategory("NATIONAL");
        electionRequest.setAdminId(1L);
        ScheduleResponse scheduleResponse = adminService.scheduleElection(electionRequest);
        assertNotNull(scheduleResponse);
        assertEquals(1,adminService.countNationalElection());
    }

}
