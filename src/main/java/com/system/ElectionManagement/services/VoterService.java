package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.*;

public interface VoterService {
    RegisterToVoteResponse register(RegisterToVoteRequest registerToVoteRequest);
    LogInResponse logIn(LogInRequest logInRequest);
    CastBallotResponse castBallot(CastBallotRequest castBallotRequest);
    UpdateInformationResponse updateVoterInfo(UpdateInformationRequest updateInformationRequest);
    LogOutResponse logOut(LogOutRequest logOutRequest);


}
