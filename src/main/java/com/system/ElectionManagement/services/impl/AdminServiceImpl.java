package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.*;
import com.system.ElectionManagement.exceptions.AdminException;
import com.system.ElectionManagement.models.SystemAdministrator;
import com.system.ElectionManagement.models.Voter;
import com.system.ElectionManagement.repositories.CandidateRepository;
import com.system.ElectionManagement.repositories.SystemAdministratorRepository;
import com.system.ElectionManagement.services.AdminService;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;

import static com.system.ElectionManagement.models.AdminPrivilege.ELECTION_MANAGER;
import static com.system.ElectionManagement.models.AdminPrivilege.SYSTEM_ADMINISTRATOR;
import static java.time.LocalDate.now;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private VoterServicesImpl voterServices;
    @Autowired
    private ElectionServiceImpl electionServices;
    @Autowired
    private CandidateServiceImpl candidateServices;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SystemAdministratorRepository systemAdministratorRepository;

    @Override
    public CandidateResponse registerCandidate(CandidateRequest candidateToBeRegistered) {
        return candidateServices.registerCandidate(candidateToBeRegistered);
    }

    @Override
    public AdminResponse registerAsAdmin(AdminRequest request) {
        SystemAdministrator check = systemAdministratorRepository.findByUsername(request.getUsername());
        if (check != null) throw new AdminException("Username already exists");
        SystemAdministrator adminToBeRegistered= SystemAdministrator.builder()
                        .firstName(request.getFirstName()).lastName(request.getLastName())
                        .adminPrivileges(List.of(SYSTEM_ADMINISTRATOR, ELECTION_MANAGER))
                        .username(request.getUsername())
                        .password(request.getPassword()).build();
        systemAdministratorRepository.save(adminToBeRegistered);
        return modelMapper.map(adminToBeRegistered, AdminResponse.class);
    }

    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {
        return voterServices.registerVoter(voterRequest);
    }

    @Override
    public ElectionResponse scheduleElection(ElectionRequest request) {
        return electionServices.scheduleElection(request);
    }

    @Override
    public RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest request) {
        return electionServices.rescheduleElection(request);
    }
}
