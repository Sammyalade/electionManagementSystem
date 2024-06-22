package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.VoterRequest;
import com.system.ElectionManagement.dtos.responses.VoterResponse;
import com.system.ElectionManagement.models.Voter;
import com.system.ElectionManagement.repositories.AddressRepository;
import com.system.ElectionManagement.repositories.ContactInformationRepository;
import com.system.ElectionManagement.repositories.VoterRepository;
import com.system.ElectionManagement.services.VoterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Period;

import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
public class VoterServicesImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final ModelMapper modelMapper;
    private final ContactInformationRepository contactInformationRepository;
    private final AddressRepository addressRepository;



    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {
        if(voterRepository.findVoterByUsernameIgnoreCase(voterRequest.getUsername())==null)throw
                new RuntimeException("something went wrong when trying to register");
        if(Period.between(voterRequest.getDateOfBirth(), now()).getYears() < 18)
            throw  new IllegalArgumentException("you are not eligible to vote");
       Voter voterToBeRegistered= Voter.builder()
                .dateOfBirth(voterRequest.getDateOfBirth())
                .lastName(voterRequest.getLastName())
                .firstName(voterRequest.getFirstName())
                .username(voterRequest.getUsername())
                .password(voterRequest.getPassword())
                .eligibilityStatus(voterRequest.getEligibilityStatus())
                .contactInformation(voterRequest.getContactInformation())
                .build();
        addressRepository.save(voterToBeRegistered.getContactInformation().getAddress());
        contactInformationRepository.save(voterToBeRegistered.getContactInformation());
        voterToBeRegistered =voterRepository.save(voterToBeRegistered);
        return modelMapper.map(voterToBeRegistered, VoterResponse.class);
    }

}
