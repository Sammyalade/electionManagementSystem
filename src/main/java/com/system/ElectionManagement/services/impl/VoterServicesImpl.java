package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.VoteRequest;
import com.system.ElectionManagement.dtos.requests.VoterRequest;
import com.system.ElectionManagement.dtos.responses.VoteResponse;
import com.system.ElectionManagement.dtos.responses.VoterResponse;
import com.system.ElectionManagement.repositories.VoterRepository;
import com.system.ElectionManagement.services.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteServicesImpl implements VoterService {
    private final VoterRepository voterRepository;


    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {

    }


    @Override
    public VoteResponse castBallot(VoteRequest voteRequest) {
        return null;
    }
}
