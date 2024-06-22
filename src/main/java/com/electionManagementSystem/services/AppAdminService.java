package com.electionManagementSystem.services;

import com.electionManagementSystem.dtos.requests.AdminRequest;
import com.electionManagementSystem.dtos.requests.ElectionRequest;
import com.electionManagementSystem.dtos.requests.UpdateElectionRequest;
import com.electionManagementSystem.dtos.responses.AdminResponse;
import com.electionManagementSystem.dtos.responses.ScheduleResponse;
import com.electionManagementSystem.dtos.responses.UpdateElectionResponse;
import com.electionManagementSystem.exceptions.InvalidElectionId;
import com.electionManagementSystem.models.Election;
import com.electionManagementSystem.models.SystemAdministrator;
import com.electionManagementSystem.repositories.AdminRepository;
import com.electionManagementSystem.repositories.ElectionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.electionManagementSystem.exceptions.ExceptionMessages.INCORRECT_ID;

@Service
public class AppAdminService implements AdminServices{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AdminRepository adminRepository;
}
