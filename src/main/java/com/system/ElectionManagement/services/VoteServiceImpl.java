package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.AddVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetAllVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetVoteRequest;
import com.system.ElectionManagement.dtos.responses.AddVoteResponse;
import com.system.ElectionManagement.dtos.responses.VoteResponse;


import java.util.List;
import java.util.Optional;

public interface VoteServiceImpl {
    List<VoteResponse> getAllVote(GetAllVoteRequest voteRequest);
    Optional<VoteResponse> getVoteById(GetVoteRequest getVoteRequest);
    AddVoteResponse addVote(AddVoteRequest voteRequest);
}
