package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.CastVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetCandidateVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetCategoryVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetVoteRequest;
import com.system.ElectionManagement.dtos.responses.AddVoteResponse;
import com.system.ElectionManagement.dtos.responses.VoteResponse;


import java.util.List;
import java.util.Optional;

public interface VoteServiceImpl {
    List<VoteResponse> getAllVoteForCategory(GetCategoryVoteRequest voteRequest);
    Optional<VoteResponse> getVoteById(GetVoteRequest getVoteRequest);
    AddVoteResponse castVote(CastVoteRequest voteRequest);
    List<VoteResponse> getAllVoteForCandidate(GetCandidateVoteRequest candidateRequest);
}
