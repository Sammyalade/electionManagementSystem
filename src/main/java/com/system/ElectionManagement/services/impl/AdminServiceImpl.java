package com.system.ElectionManagement.services.impl;


import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.*;
import com.system.ElectionManagement.models.Address;
import com.system.ElectionManagement.models.AdminPrivilege;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.models.SystemAdministrator;
import com.system.ElectionManagement.repositories.AdminRepository;
import com.system.ElectionManagement.services.AdminService;
import com.system.ElectionManagement.services.CandidateService;
import com.system.ElectionManagement.services.ElectionService;
import com.system.ElectionManagement.services.VoterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.system.ElectionManagement.models.AdminPrivilege.ELECTION_MANAGER;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private VoterService voterService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private ElectionService electionService;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CandidateResponse registerCandidate(CandidateRequest candidateToBeRegistered){
        return candidateService.registerCandidate(candidateToBeRegistered);
    }
    @Override
    public AdminResponse registerAsAdmin(AdminRequest request){
        Address address = modelMapper.map(request.getAddressRequest(),Address.class);
        ContactInformation info = modelMapper.map(request.getInfoRequest(),ContactInformation.class);
        info.setAddress(address);
        SystemAdministrator admin = modelMapper.map(request, SystemAdministrator.class);
        admin.setAdminPrivilege(ELECTION_MANAGER);
        SystemAdministrator administrator = adminRepository.save(modelMapper.map(admin, SystemAdministrator.class));
        return modelMapper.map(administrator, AdminResponse.class);
    }
    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest){
        return voterService.registerVoter(voterRequest);
    }

    @Override
    public ElectionResponse scheduleElection(ElectionRequest request) {
        return electionService.scheduleElection(request);
    }

    @Override
    public RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest request) {
        return electionService.rescheduleElection(request);
    }


}
