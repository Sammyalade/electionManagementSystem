package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.dtos.requests.ViewElectionResultRequest;
import com.system.ElectionManagement.dtos.responses.ElectionResponse;
import com.system.ElectionManagement.dtos.responses.ViewElectionResultResponse;
import com.system.ElectionManagement.models.Election;

public interface ElectionService {
    ElectionResponse scheduleElection(ElectionRequest electionRequest);
    Election findElectionByCandidateId(Long candidateId);
    Election addElection(Election election);
    ViewElectionResultResponse viewElectionResult(ViewElectionResultRequest viewResult);
}
