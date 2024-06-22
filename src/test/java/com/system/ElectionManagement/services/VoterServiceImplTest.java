package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.*;
import com.system.ElectionManagement.exceptions.InvalidRequestException;
import com.system.ElectionManagement.exceptions.InvalidVoterException;
import com.system.ElectionManagement.exceptions.UnableToLogInException;
import com.system.ElectionManagement.exceptions.UsernameAlreadyExistException;
import com.system.ElectionManagement.models.*;
import com.system.ElectionManagement.repositories.CandidateRepository;
import com.system.ElectionManagement.repositories.VoterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VoterServiceImplTest {
    @Autowired
    private VoterService voterService;
    @Autowired
    private VoterRepository voterRepository;

    @Test
    public void testThatAVoterCanSignUp() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("Joe");
        request.setPassword("password");
        request.setDateOfBirth(DateTimeFormatter.ofPattern("2002-04-05"));
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        SignUpResponse response = voterService.signUp(request);
        assertNotNull(response);
        assertTrue(response.getMessage().contains("success"));
    }


    @Test
    public void testThatVoterCanLogIn() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("Izunna");
        request.setPassword("Chukwudi");
        request.setDateOfBirth(DateTimeFormatter.ofPattern("2001-02-10"));
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        voterService.signUp(request);
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("Izunna");
        logInRequest.setPassword("Chukwudi");
        LogInResponse response = voterService.logIn(logInRequest);
        assertNotNull(response);
    }


    @Test
    public void testThatVoterCanUpdateTheirInformation() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("Ayomide");
        request.setPassword("precious");
        request.setDateOfBirth(DateTimeFormatter.ofPattern("1-04-05"));
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        voterService.signUp(request);

        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("Ayomide");
        logInRequest.setPassword("precious");
        voterService.logIn(logInRequest);

        UpdateInformationRequest updateVoterInfoRequest = new UpdateInformationRequest();
        updateVoterInfoRequest.setUsername("Ayomide");

        UpdateInformationResponse updateVoterInfoResponse = voterService.updateVoterInfo(updateVoterInfoRequest);
        assertNotNull(updateVoterInfoResponse);
        assertTrue(updateVoterInfoResponse.getMessage().contains("success"));

    }


    @Test
    public void testThatAVoterCanLogOut() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("Miriam");
        request.setPassword("admin");
        request.setDateOfBirth(DateTimeFormatter.ofPattern("2002-04-05"));
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        voterService.signUp(request);

        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("Miriam");
        logInRequest.setPassword("admin");
        voterService.logIn(logInRequest);

        LogOutRequest logOutRequest = new LogOutRequest();
        logOutRequest.setUsername("Miriam");

        LogOutResponse logOutResponse = voterService.logOut(logOutRequest);
        assertNotNull(logOutResponse);


    }


    @Test
    public void testThatAVoterCanViewTheirInformation() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("John-bull");
        request.setPassword("password");
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        voterService.signUp(request);

        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("John-bull");
        logInRequest.setPassword("password");
        voterService.logIn(logInRequest);


        ViewVoterInformationRequest viewInformationRequest = new ViewVoterInformationRequest();
        viewInformationRequest.setUsername("John-bull");

        ViewVoterInformationResponse viewInformationResponse = voterService.viewVoterInformation(viewInformationRequest);

        assertNotNull(viewInformationResponse);
        assertTrue(viewInformationResponse.getMessage().contains("success"));
    }


    @Test
    public void testThatIfAVoterHasNotRegistered_cannotLogIn(){
            LogInRequest logInRequest = new LogInRequest();
            logInRequest.setUsername("Abigail");
            logInRequest.setPassword("Okonkwo");
            assertThrows(InvalidVoterException.class, () -> voterService.logIn(logInRequest));
    }

    @Test
    public void testThatIfAVoterIsLoggedOutCannotPerformAnyOperation(){
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Abiodun");
        request.setLastName("Joel");
        request.setUsername("Abbey");
        request.setPassword("password2");
        request.setDateOfBirth(DateTimeFormatter.ofPattern("1996-08-01"));
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        var voter = voterService.signUp(request);
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("Abbey");
        logInRequest.setPassword("password2");
        voterService.logIn(logInRequest);

        LogOutRequest logOutRequest = new LogOutRequest();
        logOutRequest.setUsername("Abbey");
        voterService.logOut(logOutRequest);

        ViewVoterInformationRequest viewInformationRequest = new ViewVoterInformationRequest();
        viewInformationRequest.setUsername(voter.getUsername());

        assertThrows(InvalidRequestException.class, () -> voterService.viewVoterInformation(viewInformationRequest));
    }

    @Test
    public void testThatAVoterCannotSignUpWithAnExistingUsername() {
        SignUpRequest request = new SignUpRequest();
        SignUpRequest request1 = new SignUpRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("Abu");
        request.setPassword("password");
        request.setDateOfBirth(DateTimeFormatter.ofPattern("2002-07-11"));
        request.setEligibilityStatus(EligibilityStatus.ELIGIBLE);
        voterService.signUp(request);

        request1.setFirstName("Michael");
        request1.setLastName("Wisdom");
        request1.setUsername("Abu");
        request1.setPassword("password");
        request.setDateOfBirth(DateTimeFormatter.ofPattern("1995-09-03"));

        request1.setEligibilityStatus(EligibilityStatus.ELIGIBLE);

        assertThrows(UsernameAlreadyExistException.class, () -> voterService.signUp(request1));
    }

    }