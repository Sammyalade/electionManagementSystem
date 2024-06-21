package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.CastVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetCandidateVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetCategoryVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetVoteRequest;
import com.system.ElectionManagement.dtos.responses.AddVoteResponse;
import com.system.ElectionManagement.dtos.responses.VoteResponse;
import com.system.ElectionManagement.exceptions.CandidateNotFoundException;
import com.system.ElectionManagement.exceptions.ElectionNotStartedException;
import com.system.ElectionManagement.exceptions.VoterNotFoundException;
import com.system.ElectionManagement.models.Vote;
import com.system.ElectionManagement.repositories.CandidateRepository;
import com.system.ElectionManagement.repositories.ElectionRepository;
import com.system.ElectionManagement.repositories.VoteRepository;
import com.system.ElectionManagement.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoteServices implements VoteServiceImpl {

    private final VoteRepository voteRepository;
    private final ModelMapper modelMapper;
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;
    private final ElectionRepository electionRepository;

    @Override
    public List<VoteResponse> getAllVoteForCategory(GetCategoryVoteRequest voteRequest) {
        List<Vote> votes = voteRepository.findVotesByCategory(voteRequest.getElectionCategory());
        if (votes.isEmpty()) {
            throw new VoterNotFoundException("No votes found");
        }
        return voteRepository.findAll()
                .stream()
                .map(vote -> modelMapper.map(vote, VoteResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VoteResponse> getVoteById(GetVoteRequest getVoteRequest) {
        Optional<Vote> checkVote = voteRepository.findById(getVoteRequest.getVoteId());
        if(checkVote.isEmpty()){
            throw new VoterNotFoundException("No vote found");
        }
        return voteRepository.findById(getVoteRequest.getVoteId())
                .map(vote -> modelMapper.map(vote, VoteResponse.class));
    }

    @Override
    public AddVoteResponse castVote(CastVoteRequest voteRequest) {
            var voter = voterRepository.findById(voteRequest.getId());
            if(voter.isEmpty()){
                throw new VoterNotFoundException("No voter found");
            }
            var election = electionRepository.findById(voteRequest.getElectionId());
            if(election.isEmpty()){
                throw new ElectionNotStartedException("No election started");
            }
            var existingVote = voteRepository.findByVoterAndElection(voteRequest.getId(), election.get().getId());
            if (existingVote.isPresent()) {
                throw new IllegalStateException("Voter has already cast a vote in this election");
            }
            Vote voteEntity = modelMapper.map(voteRequest, Vote.class);
            voteEntity.setId(voteRequest.getId());
            voteEntity.setVoter(voter.get());
            voteEntity.setElection(election.get());
            var candidate = candidateRepository.findById(voteRequest.getCandidateId());
            if (candidate.isEmpty()) {
                throw new CandidateNotFoundException("No candidate found");
            }
            voteEntity.setCandidate(candidate.get());
            voteEntity =  voteRepository.save(voteEntity);
            return modelMapper.map(voteEntity, AddVoteResponse.class);
    }

    @Override
    public List<VoteResponse> getAllVoteForCandidate(GetCandidateVoteRequest candidateRequest) {
        List<Vote> votes = voteRepository.findVotesByCandidateId(candidateRequest.getCandidateId());
        if (votes.isEmpty()) {
            throw new VoterNotFoundException("No votes found");
        }
        return voteRepository.findAll()
                .stream()
                .map(vote -> modelMapper.map(vote, VoteResponse.class))
                .collect(Collectors.toList());
    }
}
