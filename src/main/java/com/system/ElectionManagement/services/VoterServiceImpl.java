package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.*;
import com.system.ElectionManagement.models.Vote;
import com.system.ElectionManagement.models.Voter;
import com.system.ElectionManagement.repositories.CandidateRepository;
import com.system.ElectionManagement.repositories.VoteRepository;
import com.system.ElectionManagement.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor

public class VoterServiceImpl implements VoterService{
    private final VoterRepository voterRepository;
    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public VoterServiceImpl(VoterRepository voterRepository,
                            ModelMapper modelMapper, VoteRepository voteRepository, CandidateRepository candidateRepository){
        this.voterRepository = voterRepository;
        this.modelMapper = modelMapper;
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
    }
    @Override
    public RegisterToVoteResponse register(RegisterToVoteRequest registerToVoteRequest) {
        Voter voter = modelMapper.map(registerToVoteRequest, Voter.class);
        voterRepository.save(voter);
        var response = modelMapper.map(voter, RegisterToVoteResponse.class);
        response.setMessage("Voter successfully signed up");

        return response;
    }

    @Override
    public LogInResponse logIn(LogInRequest logInRequest) {
        Voter voter = modelMapper.map(logInRequest, Voter.class);
        voter = voterRepository.save(voter);
        var response = modelMapper.map(logInRequest,LogInResponse.class);
        response.setMessage("Voter successfully signed");
        return response;
    }

    @Override
    public CastBallotResponse castBallot(CastBallotRequest castBallotRequest) {
        Vote vote = new Vote();
        if(!(voterRepository.existsById(castBallotRequest.getVoterId()) || candidateRepository.existsById(castBallotRequest.getVoterId())))
            throw new RuntimeException("cannot cast ballot");

        vote.setId(castBallotRequest.getVoterId());
        vote.setCandidate(castBallotRequest.getCandidate());
        vote.setTimeVoted(castBallotRequest.getTimeOfRequest());
        vote.setElection(castBallotRequest.getElection());
        voteRepository.save(vote);
        var votes = voteRepository.findVotesByCandidateId(castBallotRequest.getCandidate());
        CastBallotResponse castBallotResponse = new CastBallotResponse();
        castBallotResponse.setMessage("Ballot successfully casted to " + castBallotRequest.getCandidate());
        castBallotResponse.setNumberOfVote(votes.size());

        return castBallotResponse;
    }


    @Override
    public UpdateInformationResponse updateVoterInfo(UpdateInformationRequest updateInformationRequest) {
        Voter voter = voterRepository.findVoterById(updateInformationRequest.getId());

        if(voter == null) throw new RuntimeException("This voter does not exist");
        modelMapper.map(updateInformationRequest, voter);
        voterRepository.save(voter);
        UpdateInformationResponse response = modelMapper.map(updateInformationRequest,  UpdateInformationResponse.class);
        response.setMessage("Voter information successfully updated");
        response.setTimeUpdated(LocalDateTime.now());
        return response;
    }

    @Override
    public LogOutResponse logOut(LogOutRequest logOutRequest) {
        LogOutResponse logOutResponse = new LogOutResponse();
        Voter voter = findVoter(logOutRequest.getId());
        if(voter != null) {
            logOutResponse.setMessage("Voter successfully logged out");
        } else {
            logOutResponse.setMessage("This voter does not exist");
        }
        return logOutResponse;
    }

    public Voter findVoter(Long id) {

    return voterRepository.findById(id).orElse(null);}

}


