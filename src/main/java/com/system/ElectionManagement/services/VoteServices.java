package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.AddVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetVoteRequest;
import com.system.ElectionManagement.dtos.responses.AddVoteResponse;
import com.system.ElectionManagement.dtos.responses.VoteResponse;
import com.system.ElectionManagement.exceptions.VoterNotFoundException;
import com.system.ElectionManagement.models.Vote;
import com.system.ElectionManagement.repositories.VoteRepository;
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

    @Override
    public List<VoteResponse> getAllVote() {
        List<Vote> votes = voteRepository.findAll();
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
    public AddVoteResponse addVote(AddVoteRequest voteRequest) {
        voteRepository.findById(voteRequest.getId());
            Vote voteEntity = modelMapper.map(voteRequest, Vote.class);
            voteEntity.setId(voteRequest.getId());
            voteEntity.setElection(voteRequest.getElection());
            voteEntity =  voteRepository.save(voteEntity);
            return modelMapper.map(voteEntity, AddVoteResponse.class);
    }
}
