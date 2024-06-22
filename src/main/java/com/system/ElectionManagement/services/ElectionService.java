package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.dtos.requests.RescheduleElectionRequest;
import com.system.ElectionManagement.dtos.responses.ElectionResponse;
import com.system.ElectionManagement.dtos.responses.RescheduleElectionResponse;

public interface ElectionService {
    RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest rescheduleRequest);

    ElectionResponse scheduleElection (ElectionRequest electionRequest);
}
