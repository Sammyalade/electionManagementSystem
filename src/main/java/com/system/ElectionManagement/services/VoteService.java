package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.VoteRequest;
import com.system.ElectionManagement.dtos.responses.VoteResponse;

public interface VoteServices {
    VoteResponse castBallot (VoteRequest voteRequest);

}
