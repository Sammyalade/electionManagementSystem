package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.VoterRequest;
import com.system.ElectionManagement.dtos.responses.VoterResponse;

public interface VoterService {
    VoterResponse registerVoter(VoterRequest voterRequest);
}
