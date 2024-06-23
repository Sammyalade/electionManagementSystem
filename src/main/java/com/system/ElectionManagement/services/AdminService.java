package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.CandidateResponse;
import com.system.ElectionManagement.dtos.responses.ElectionResponse;
import com.system.ElectionManagement.dtos.responses.RescheduleElectionResponse;
import com.system.ElectionManagement.dtos.responses.VoterResponse;
import com.system.ElectionManagement.dtos.responses.AdminResponse;

public interface AdminService {
    CandidateResponse registerCandidate(CandidateRequest candidateToBeRegistered);
    AdminResponse registerAsAdmin(AdminRequest request);
    VoterResponse registerVoter(VoterRequest voterRequest);
    ElectionResponse scheduleElection(ElectionRequest request);
    RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest request);
}
