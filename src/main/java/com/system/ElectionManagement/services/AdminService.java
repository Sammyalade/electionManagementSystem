package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.*;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    CandidateResponse registerCandidate(CandidateRequest candidateToBeRegistered);
    AdminResponse registerAsAdmin(AdminRequest request);
    VoterResponse registerVoter(VoterRequest voterRequest);
    ElectionResponse scheduleElection(ElectionRequest request);
    RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest request);


}
