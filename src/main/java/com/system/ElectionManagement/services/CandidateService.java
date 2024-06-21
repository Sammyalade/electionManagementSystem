package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.CandidateRequest;
import com.system.ElectionManagement.dtos.responses.CandidateResponse;

public interface CandidateService {
    CandidateResponse registerCandidate(CandidateRequest candidateRequest);


}
