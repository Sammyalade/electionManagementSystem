package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.*;
import com.system.ElectionManagement.exceptions.InvalidRequestException;
import com.system.ElectionManagement.exceptions.InvalidVoterException;
import com.system.ElectionManagement.exceptions.UnableToLogInException;
import com.system.ElectionManagement.exceptions.UsernameAlreadyExistException;
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

public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VoterServiceImpl(VoterRepository voterRepository,
                            ModelMapper modelMapper, VoteRepository voteRepository, CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.modelMapper = modelMapper;
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        Voter voter = new Voter();
        modelMapper.map(signUpRequest, voter);
        if(isAValidVoter(voter)) voterRepository.save(voter);
        else throw new UsernameAlreadyExistException("A voter with this username already exists");
        SignUpResponse response = modelMapper.map(voter, SignUpResponse.class);
        response.setMessage("Voter successfully signed up");
        return response;
    }

    @Override
    public LogInResponse logIn(LogInRequest logInRequest) {
        Voter voter = findVoterByUsername(logInRequest.getUsername());
        if(voter == null) {
            throw new InvalidVoterException("This voter does not exist");
        }
        if (voter.getUsername().equalsIgnoreCase(logInRequest.getUsername())) voter.setLoggedIn(true);
        else throw new UnableToLogInException("Invalid username or password");
        if (voter.getPassword().equalsIgnoreCase(logInRequest.getPassword())) voter.setLoggedIn(true);
        else throw new UnableToLogInException("Invalid username or password");
        voterRepository.save(voter);
       LogInResponse logInResponse = modelMapper.map(voter, LogInResponse.class);
       logInResponse.setMessage("Voter successfully logged in");
        return logInResponse;
    }



    private boolean isAValidVoter (Voter voter){
            return voterRepository.findVoterByUsername(voter.getUsername()) == null;
        }

        @Override
        public UpdateInformationResponse updateVoterInfo (UpdateInformationRequest updateInformationRequest){
            Voter voter = voterRepository.findVoterByUsername(updateInformationRequest.getUsername());

            if (voter == null) throw new RuntimeException("This voter does not exist");
            modelMapper.map(updateInformationRequest, voter);
            voterRepository.save(voter);
            UpdateInformationResponse response = modelMapper.map(updateInformationRequest, UpdateInformationResponse.class);
            response.setMessage("Voter information successfully updated");
            response.setTimeUpdated(LocalDateTime.now());
            return response;
        }

        @Override
        public LogOutResponse logOut (LogOutRequest logOutRequest){
            Voter voter = findVoterByUsername(logOutRequest.getUsername()) ;
            if (voter.isLoggedIn()) voter.setLoggedIn(false);
            else throw new InvalidRequestException("Invalid request");
            voterRepository.save(voter);
           LogOutResponse logOutResponse = modelMapper.map(voter, LogOutResponse.class);
            logOutResponse.setMessage("successfully logged out");
            return logOutResponse;
        }

        @Override
        public ViewVoterInformationResponse viewVoterInformation (ViewVoterInformationRequest
        viewVoterInformationRequest){
            Voter voter = voterRepository.findVoterByUsername(viewVoterInformationRequest.getUsername());
            if (voter.isLoggedIn()) voter.setLoggedIn(true);
            else throw new InvalidRequestException("You have to log in first");
            modelMapper.map(viewVoterInformationRequest, voter);
            voterRepository.save(voter);
            ViewVoterInformationResponse viewVoterInformationResponse = modelMapper.map(viewVoterInformationRequest, ViewVoterInformationResponse.class);
            viewVoterInformationResponse.setMessage("successfully viewed successfully");
            viewVoterInformationResponse.setTimeOfResponse(LocalDateTime.now());
            return viewVoterInformationResponse;
        }




    private Voter findVoterByUsername(String username) {
        return voterRepository.findVoterByUsername(username);
    }
}


