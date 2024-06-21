package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.RescheduleElectionRequest;
import com.system.ElectionManagement.dtos.responses.RescheduleElectionResponse;

public interface ElectionService {
    RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest rescheduleRequest);

}
