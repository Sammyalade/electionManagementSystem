package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.NominationRequest;
import com.system.ElectionManagement.dtos.responses.NominationResponse;

import java.util.List;

public interface NominationFormService {
    NominationResponse submit(NominationRequest nominationRequest);


    NominationResponse findById(long id);

    List<NominationResponse> findAllNominationForms();

    NominationResponse rejectNomination(Long id);

    NominationResponse approveNomination(Long id);

}
