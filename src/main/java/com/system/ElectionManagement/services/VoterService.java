package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.*;

public interface VoterService {
    SignUpResponse signUp(SignUpRequest signUpRequest);
    LogInResponse logIn(LogInRequest logInRequest);
    UpdateInformationResponse updateVoterInfo(UpdateInformationRequest updateInformationRequest);
    LogOutResponse logOut(LogOutRequest logOutRequest);

    ViewVoterInformationResponse viewVoterInformation(ViewVoterInformationRequest viewVoterInformation);


}
