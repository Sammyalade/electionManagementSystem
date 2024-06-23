package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.dtos.requests.ViewElectionResultRequest;
import com.system.ElectionManagement.dtos.responses.ElectionResponse;
import com.system.ElectionManagement.dtos.responses.ViewElectionResultResponse;
import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.repositories.CandidateRepository;
import com.system.ElectionManagement.repositories.ElectionRepository;
import com.system.ElectionManagement.services.ElectionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


import static com.system.ElectionManagement.models.ElectionStatus.SCHEDULED;

@Service
@RequiredArgsConstructor
public class ElectionServiceImpl implements ElectionService {

    private final ElectionRepository electionRepository;

    private final CandidateRepository candidateRepository;
    @Override
    public ElectionResponse scheduleElection(ElectionRequest electionRequest) {
             if(!isValidTimeOfElection(LocalDateTime.parse(electionRequest.getStartTime())
                     , LocalDateTime.parse(electionRequest.getEndTime())))
                 throw new RuntimeException("something is wrong with your timing");
        Election electionToBeScheduled = Election.builder()
                .electionCategory(electionRequest.getElectionCategory())
                .electionTitle(electionRequest.getElectionTitle())
                .electionStatus(SCHEDULED)
                .candidates(electionRequest.getCandidateId().stream()
                        .map(candidateRepository::findCandidateById)
                        .collect(Collectors.toSet()))
                .startTime(LocalDateTime.parse(electionRequest.getStartTime()))
                .endTime(LocalDateTime.parse(electionRequest.getEndTime()))
                .build();
        electionToBeScheduled =electionRepository.save(electionToBeScheduled);
        return ElectionResponse.builder()
                .id(electionToBeScheduled.getId())
                .electionStatus(electionToBeScheduled.getElectionStatus())
                .electionCategory(electionToBeScheduled.getElectionCategory())
                .endTime(String.valueOf(electionToBeScheduled.getEndTime()))
                .startTime(String.valueOf(electionToBeScheduled.getStartTime()))
                .candidates(electionToBeScheduled.getCandidates())
                .electionTitle(electionToBeScheduled.getElectionTitle())
                .build();
    }

    @Override
    public Election findElectionByCandidateId(Long candidateId) {
                  for(Election election : electionRepository.findAll()){
                      Set<Long> candidateIds =election.getCandidates().stream().map(Candidate::getId).collect(Collectors.toSet());
                      if(candidateIds.contains(candidateId))return election;
                  }
                  return null;
    }

    @Override
    public Election addElection(Election election) {
     return electionRepository.save(election);
    }




    private boolean isValidTimeOfElection(LocalDateTime timeStarted,LocalDateTime timeEnded){
        return !timeStarted.isBefore(LocalDateTime.now())
                && !timeStarted.isAfter(timeEnded)
                && !timeEnded.isBefore(timeStarted)
                && !timeEnded.isBefore(LocalDateTime.now());

    }


    @Override
    public ViewElectionResultResponse viewElectionResult(ViewElectionResultRequest viewResult) {
        Election election = electionRepository
                .findElectionById(viewResult.getElectionId());
        ElectionResult electionResult = election.getElectionResult();
        ViewElectionResultResponse resultResponse = modelMapper
                .map(electionResult, ViewElectionResultResponse.class);
        resultResponse.setElectionId(election.getId());
        resultResponse.setElectionTitle(election.getElectionTitle());
        resultResponse.setStartTime(election.getStartTime());
        resultResponse.setEndTime(election.getEndTime());
        resultResponse.setElectionStatus(election.getElectionStatus());
        resultResponse.setElectionCategory(election.getElectionCategory());
        if (electionResult.getCandidate() != null) {
            String fullName = electionResult.getCandidate()
                    .getFirstName() + " " + electionResult.getCandidate().getLastName();
            resultResponse.setCandidateName(fullName);
        }

        return resultResponse;
    }

}
