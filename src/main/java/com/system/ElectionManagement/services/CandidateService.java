package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.responses.CandidateResponse;
import com.system.ElectionManagement.models.CandidateRequest;

public interface CandidateService {
    CandidateResponse registerCandidate(com.system.ElectionManagement.dtos.requests.CandidateRequest candidateRequest);
    CandidateRequest addCandidate(CandidateRequest candidateRequest);
    CandidateRequest findCandidateById(Long id);
}
