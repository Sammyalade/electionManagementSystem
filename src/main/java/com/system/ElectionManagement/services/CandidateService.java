package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.CandidateRequest;
import com.system.ElectionManagement.dtos.responses.CandidateResponse;
import com.system.ElectionManagement.models.Candidate;

public interface CandidateService {
    CandidateResponse registerCandidate(CandidateRequest candidateRequest);
    Candidate addCandidate(Candidate candidate);
    Candidate findCandidateById(Long id);
}
