package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.VoteRequest;
import com.system.ElectionManagement.dtos.requests.VoterRequest;
import com.system.ElectionManagement.dtos.responses.VoteResponse;
import com.system.ElectionManagement.dtos.responses.VoterResponse;
import com.system.ElectionManagement.models.Voter;

public interface VoterService {
    VoterResponse registerVoter(VoterRequest voterRequest);

    VoteResponse castBallot (VoteRequest voteRequest);
}
