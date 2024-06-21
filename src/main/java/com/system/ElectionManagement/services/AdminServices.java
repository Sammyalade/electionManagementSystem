package com.electionManagementSystem.services;

import com.electionManagementSystem.dtos.requests.AdminRequest;
import com.electionManagementSystem.dtos.requests.ElectionRequest;
import com.electionManagementSystem.dtos.responses.AdminResponse;
import com.electionManagementSystem.dtos.responses.ScheduleResponse;
import org.springframework.stereotype.Service;

@Service
public interface AdminServices {

    ScheduleResponse scheduleElection(ElectionRequest electionRequest);

    AdminResponse createAdmin(AdminRequest admin);

    int countNationalElection();
}
