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
    private final ModelMapper modelMapper;
    @Autowired
    public VoterServiceImpl(VoterRepository voterRepository,
                            ModelMapper modelMapper, VoteRepository voteRepository){
        this.voterRepository = voterRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        Voter voter = modelMapper.map(signUpRequest, Voter.class);
        voterRepository.save(voter);
        var response = modelMapper.map(voter, SignUpResponse.class);
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

    @Override
    public ViewVoterInformationResponse viewVoterInformation(ViewVoterInformationRequest viewVoterInformationRequest) {
        Voter voter = voterRepository.findVoterById(viewVoterInformationRequest.getId());
        if(voter == null) throw new RuntimeException("This voter does not exist");
        modelMapper.map(viewVoterInformationRequest, voter);
        voterRepository.save(voter);
        ViewVoterInformationResponse viewVoterInformationResponse = modelMapper.map(viewVoterInformationRequest,  ViewVoterInformationResponse.class);
        viewVoterInformationResponse.setMessage("successfully viewed successfully");
        viewVoterInformationResponse.setTimeOfResponse(LocalDateTime.now());
        return viewVoterInformationResponse;
    }

    public Voter findVoter(Long id) {

    return voterRepository.findById(id).orElse(null);}

}


