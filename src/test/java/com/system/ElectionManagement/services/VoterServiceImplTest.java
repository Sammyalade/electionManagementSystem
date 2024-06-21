package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.*;
import com.system.ElectionManagement.models.*;
import com.system.ElectionManagement.repositories.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VoterServiceImplTest {
    @Autowired
    private VoterService voterService;
    private CandidateRepository candidateRepository; ;

    @Test
   public void testThatAVoterCanRegister() {
        RegisterToVoteRequest request = new RegisterToVoteRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("Joe");
        request.setPassword("password");
        request.setDateOfBirth(LocalDateTime.now());
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        RegisterToVoteResponse response = voterService.register(request);
        assertNotNull(response);
        assertTrue(response.getMessage().contains("success"));
    }

    @Test
    public void testThatTwoVotersCanRegister() {
        RegisterToVoteRequest request = new RegisterToVoteRequest();
        RegisterToVoteRequest request1 = new RegisterToVoteRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("Joe");
        request.setPassword("password");
        request.setDateOfBirth(LocalDateTime.now());
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);

        request1.setFirstName("Joseph");
        request1.setLastName("David");
        request1.setUsername("Jude");
        request1.setPassword("password");
        request1.setDateOfBirth(LocalDateTime.now());
        request1.setEligibilityStatus(EligibilityStatus.NOT_ELIGIBLE);


        RegisterToVoteResponse response = voterService.register(request);
        RegisterToVoteResponse response1 = voterService.register(request1);
        assertNotNull(response);
        assertTrue(response.getMessage().contains("success"));
        assertNotNull(response1);
        assertTrue(response1.getMessage().contains("success"));
    }

    @Test
    public void testThatVoterCanLogIn() {
        RegisterToVoteRequest request = new RegisterToVoteRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("John-bull");
        request.setId(300L);
        request.setPassword("password");
        request.setDateOfBirth(LocalDateTime.now());
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        voterService.register(request);

        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("John-bull");
        logInRequest.setPassword("password");

        LogInResponse response = voterService.logIn(logInRequest);
        assertNotNull(response);
        assertTrue(response.getMessage().contains("success"));
    }

    @Sql(scripts = {"/db/data.sql"})

    @Test
    public void testThatVoterCanCastBallot() {

        CastBallotRequest castBallotRequest = new CastBallotRequest();
//        castBallotRequest.setVoterId(id);
        castBallotRequest.setCandidate(new Candidate());
        castBallotRequest.setElection(new Election());
        castBallotRequest.setTimeOfRequest(LocalDateTime.now());
        CastBallotResponse castBallotResponse = voterService.castBallot(castBallotRequest);
        assertThat(castBallotResponse.getMessage()).isNotNull();
        assertThat(castBallotResponse.getNumberOfVote()).isEqualTo(1);
    }

    @Test
   public void testThatVoterCanUpdateTheirInformation() {
        RegisterToVoteRequest request = new RegisterToVoteRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("John-bull");
        request.setPassword("password");
        request.setDateOfBirth(LocalDateTime.now());
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        var voter =voterService.register(request);

        UpdateInformationRequest updateVoterInfoRequest = new UpdateInformationRequest();
        updateVoterInfoRequest.setId(voter.getId());
        updateVoterInfoRequest.setFirstName("Gideon");
        updateVoterInfoRequest.setLastName("Ayomide");
        updateVoterInfoRequest.setUsername("Tosho");
        updateVoterInfoRequest.setPassword("password");

        UpdateInformationResponse updateVoterInfoResponse = voterService.updateVoterInfo(updateVoterInfoRequest);
        assertNotNull(updateVoterInfoResponse);
        assertTrue(updateVoterInfoResponse.getMessage().contains("success"));

    }


    @Test
    public void testThatAVoterCanLogOut(){
        RegisterToVoteRequest request = new RegisterToVoteRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("John-bull");
        request.setId(300L);
        request.setDateOfBirth(LocalDateTime.now());
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        voterService.register(request);

        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("John-bull");
        logInRequest.setPassword("password");
        voterService.logIn(logInRequest);

        LogOutRequest logOutRequest = new LogOutRequest();
        logOutRequest.setId(300L);

        LogOutResponse logOutResponse = voterService.logOut(logOutRequest);

        assertNotNull(logOutResponse);


    }
}