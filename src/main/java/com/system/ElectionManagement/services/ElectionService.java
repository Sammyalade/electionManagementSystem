package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.dtos.responses.ElectionResponse;

public interface ElectionService {
      ElectionResponse scheduleElection (ElectionRequest electionRequest);
}
