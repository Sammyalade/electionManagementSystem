package com.system.ElectionManagement.services;
import com.system.ElectionManagement.dtos.requests.NominationRequest;
import com.system.ElectionManagement.dtos.responses.NominationResponse;
import com.system.ElectionManagement.exceptions.NominationNotFoundException;
import com.system.ElectionManagement.models.NominationForm;
import com.system.ElectionManagement.models.NominationStatus;
import com.system.ElectionManagement.repositories.NominationFormRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.system.ElectionManagement.models.NominationStatus.REJECTED;

@Service
public class NominationFromServiceImpl implements NominationFormService {
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private NominationFormRepository nominationFormRepository;

    @Override
    public NominationResponse submit(NominationRequest nominationRequest) {
        if (nominationRequest.getPositionContested() == null || nominationRequest.getDateSubmitted() == null) {
            throw new RuntimeException("Invalid nomination data");
        }

        NominationForm nominationForm = modelMapper.map(nominationRequest, NominationForm.class);
        if (nominationForm.getDateApproved() == null) {
            nominationForm.setDateApproved(LocalDateTime.now());
        }
        nominationForm = nominationFormRepository.save(nominationForm);
        return modelMapper.map(nominationForm, NominationResponse.class);
    }

    @Override
    public NominationResponse findById(long id) {
        NominationForm nominationForm = nominationFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nomination not found for id: " + id));


        NominationResponse nominationResponse = new NominationResponse();
        nominationResponse.setId(nominationForm.getId());
        nominationResponse.setPositionContested(nominationForm.getPositionContested());
        nominationResponse.setDateApproved(nominationForm.getDateApproved());
        nominationResponse.setDateSubmitted(nominationForm.getDateSubmitted());
        nominationResponse.setNominationStatus(nominationForm.getStatus());
        nominationResponse.setElectionCategory(nominationForm.getElectionCategory());

        return nominationResponse;
    }

    @Override
    public List<NominationResponse> findAllNominationForms() {
        List<NominationForm> nominationForms = nominationFormRepository.findAll();
        return nominationForms.stream()
                .map(nominationForm -> modelMapper.map(nominationForm, NominationResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public NominationResponse rejectNomination(Long id) {
        NominationForm nominationForm = nominationFormRepository.findById(id)
                .orElseThrow(() -> new NominationNotFoundException("Nomination not found for id: " + id));

        if (nominationForm.getStatus() == REJECTED) {
            throw new RuntimeException("Nomination is already rejected");
        }

        nominationForm.setStatus(REJECTED);
        nominationForm = nominationFormRepository.save(nominationForm);
        return modelMapper.map(nominationForm, NominationResponse.class);

    }

    @Override
    public NominationResponse approveNomination(Long id) {
        NominationForm nominationForm = nominationFormRepository.findById(id)
                .orElseThrow(() -> new NominationNotFoundException("Nomination not found for id: " + id));
        nominationForm.setStatus(NominationStatus.APPROVED);
        nominationForm.setDateApproved(LocalDateTime.now());
        NominationForm savedForm = nominationFormRepository.save(nominationForm);
        return modelMapper.map(savedForm, NominationResponse.class);
    }

}