package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.VoteRequest;
import com.system.ElectionManagement.dtos.responses.VoteResponse;
import com.system.ElectionManagement.models.ElectionStatus;
import com.system.ElectionManagement.models.Vote;
import com.system.ElectionManagement.repositories.VoteRepository;
import com.system.ElectionManagement.repositories.VoterRepository;
import com.system.ElectionManagement.services.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.system.ElectionManagement.models.ElectionStatus.IN_PROGRESS;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private  final CandidateServiceImpl candidateService;
    private final ElectionServiceImpl electionService;
    private final VoterRepository voterRepository;
    private final VoteRepository voteRepository;

    @Override
    public VoteResponse castBallot(VoteRequest voteRequest) {
           var election =electionService.findElectionByCandidateId(voteRequest.getCandidateId());
           if(election.getStartTime().isBefore(now()))election.setElectionStatus(IN_PROGRESS);
           else throw new RuntimeException("election never start bro keep ur card");
           electionService.addElection(election);
           var voter = voterRepository.findVoterById(voteRequest.getVoterId());
           var candidate = candidateService.findCandidateById(voteRequest.getCandidateId());
           if(voter ==null ||candidate == null)throw new RuntimeException("something went wrong");
           Vote vote  
    }
}
