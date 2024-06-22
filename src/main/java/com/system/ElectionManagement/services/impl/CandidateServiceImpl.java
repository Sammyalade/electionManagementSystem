package com.system.ElectionManagement.services.impl;////////package com.system.ElectionManagement.services.impl;

import com.cloudinary.Cloudinary;
import com.system.ElectionManagement.dtos.requests.CandidateRequest;
import com.system.ElectionManagement.dtos.responses.CandidateResponse;
import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.repositories.CandidateRepository;
import com.system.ElectionManagement.repositories.ContactInformationRepository;
import com.system.ElectionManagement.repositories.ElectionRepository;
import com.system.ElectionManagement.services.CandidateService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final Cloudinary cloudinary;
    
    private final ModelMapper modelMapper;

    private final CandidateRepository candidateRepo;

    private final ElectionRepository electionRepository;

    private final ContactInformationRepository contactInformationRepository;


    @Override
    public CandidateResponse registerCandidate(CandidateRequest candidateRequest) {
        try {
            Map<?,?> nominationFormResponse = cloudinary.uploader().upload(candidateRequest.getNominationForm().getBytes(), new HashMap());
            Map<?,?> financialFormResponse = cloudinary.uploader().upload(candidateRequest.getFinancialDisclosureForm().getBytes(),new HashMap());
            String nominationFormUrl = nominationFormResponse.get("url").toString();
            String financialFormUrl = financialFormResponse.get("url").toString();
            var candidate=modelMapper.map(candidateRequest, Candidate.class);
            candidate.setFinancialDisclosureUrl(nominationFormUrl);
            candidate.setNominationFormUrl(financialFormUrl);
            candidate.setContactInformation(candidateRequest.getContactInformation());
            if(Period.between(candidate.getDateOfBirth(), now()).getYears()<18)throw new IllegalArgumentException("you are to young to vote");
            contactInformationRepository.save(candidate.getContactInformation());
            candidate = candidateRepo.save(candidate);
            return modelMapper.map(candidate, CandidateResponse.class);
        }catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("something went wrong");
        }
    }

    @Override
    public Candidate addCandidate(Candidate candidate) {
        return candidateRepo.save(candidate);
    }

    @Override
    public Candidate findCandidateById(Long id) {
        return candidateRepo.findCandidateById(id);
    }


}
