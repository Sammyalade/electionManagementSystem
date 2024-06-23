package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.dtos.requests.RescheduleElectionRequest;
import com.system.ElectionManagement.dtos.responses.ElectionResponse;
import com.system.ElectionManagement.dtos.responses.RescheduleElectionResponse;
import com.system.ElectionManagement.exceptions.CantRescheduleElectionException;
import com.system.ElectionManagement.exceptions.ElectionNotFoundException;
import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.ElectionStatus;
import com.system.ElectionManagement.repositories.CandidateRepository;
import com.system.ElectionManagement.repositories.ElectionRepository;
import com.system.ElectionManagement.services.ElectionService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest request) {
        RescheduleElectionResponse response = new RescheduleElectionResponse();

        Long electionId = Long.parseLong(String.valueOf(request.getElectionId()));
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new ElectionNotFoundException("Election not found"));

        if (election.getElectionStatus() == ElectionStatus.IN_PROGRESS || election.getElectionStatus() == ElectionStatus.CONCLUDED) {
            throw new CantRescheduleElectionException("Cannot reschedule an ongoing or completed election");
        }

        if (request.getElectionDate() == null) {
            throw new ElectionNotFoundException("Invalid date");
        }

        election.setStartTime(request.getElectionDate());
        election.setElectionStatus(ElectionStatus.SCHEDULED);
        Election updatedElection = electionRepository.save(election);

        response.setMessage("Election rescheduled successfully");
        response.setRescheduledElectionId(String.valueOf(updatedElection.getId()));
        response.setRescheduledElectionDate(updatedElection.getStartTime());
        return response;
}
    private boolean isValidTimeOfElection(LocalDateTime timeStarted,LocalDateTime timeEnded){
        return !timeStarted.isBefore(LocalDateTime.now())
                && !timeStarted.isAfter(timeEnded)
                && !timeEnded.isBefore(timeStarted)
                && !timeEnded.isBefore(LocalDateTime.now());


}



}
