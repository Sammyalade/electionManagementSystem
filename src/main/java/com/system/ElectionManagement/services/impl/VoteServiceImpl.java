package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.VoteRequest;
import com.system.ElectionManagement.dtos.responses.VoteResponse;
import com.system.ElectionManagement.models.ElectionStatus;
import com.system.ElectionManagement.models.EligibilityStatus;
import com.system.ElectionManagement.models.Vote;
import com.system.ElectionManagement.models.Voter;
import com.system.ElectionManagement.repositories.VoteRepository;
import com.system.ElectionManagement.repositories.VoterRepository;
import com.system.ElectionManagement.services.VoteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.system.ElectionManagement.models.ElectionStatus.IN_PROGRESS;
import static com.system.ElectionManagement.models.EligibilityStatus.NOT_ELIGIBLE;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final CandidateServiceImpl candidateService;
    private final ElectionServiceImpl electionService;
    private final VoterRepository voterRepository;
    private final VoteRepository voteRepository;
    private final ModelMapper modelMapper;

    @Override
    public VoteResponse castBallot(VoteRequest voteRequest) {
           var election =electionService.findElectionByCandidateId(voteRequest.getCandidateId());
           if(election.getStartTime().isBefore(now()))election.setElectionStatus(IN_PROGRESS);
           else throw new RuntimeException("election never start bro keep ur card");
           electionService.addElection(election);
           var voter = voterRepository.findVoterById(voteRequest.getVoterId());
           var candidate = candidateService.findCandidateById(voteRequest.getCandidateId());
           if(voter ==null ||candidate == null)throw new RuntimeException("something went wrong");
           if(voter.getEligibilityStatus()==NOT_ELIGIBLE)throw new RuntimeException("you already voted for a candidate");
           var vote = Vote.builder().voter(voter).candidate(candidate).timeVoted(now()).build();
           if(voter.getEligibilityStatus()!= NOT_ELIGIBLE)voter.setEligibilityStatus(NOT_ELIGIBLE);
           vote =voteRepository.save(vote);candidate.getVotes().add(vote);candidateService.addCandidate(candidate);
           return modelMapper.map(vote,VoteResponse.class);
    }
}
