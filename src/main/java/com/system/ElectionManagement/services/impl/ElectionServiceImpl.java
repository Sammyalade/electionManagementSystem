package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.CandidateInfo;
import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.dtos.requests.RescheduleElectionRequest;
import com.system.ElectionManagement.dtos.requests.ViewElectionResultRequest;
import com.system.ElectionManagement.dtos.responses.ElectionResponse;
import com.system.ElectionManagement.dtos.responses.RescheduleElectionResponse;
import com.system.ElectionManagement.dtos.responses.ViewElectionResultResponse;
import com.system.ElectionManagement.exceptions.ElectionNotFoundException;
import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.ElectionResult;
import com.system.ElectionManagement.repositories.CandidateRepository;
import com.system.ElectionManagement.repositories.ElectionRepository;
import com.system.ElectionManagement.services.ElectionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import static com.system.ElectionManagement.models.ElectionStatus.*;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class ElectionServiceImpl implements ElectionService {

    private final ElectionRepository electionRepository;

    private final CandidateRepository candidateRepository;

    private final ModelMapper modelMapper;
    @Override
    public ElectionResponse scheduleElection(ElectionRequest electionRequest) {
                 if(!isValidTimeOfElection(LocalDateTime.parse(electionRequest.getStartTime()), LocalDateTime.parse(electionRequest.getEndTime())))
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
                 if(electionRequest.getId()!=null)electionToBeScheduled.setId(electionRequest.getId());
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
            var election=electionRepository.findElectionById(request.getElectionId());
            if(election==null || election.getElectionStatus()==IN_PROGRESS)throw new ElectionNotFoundException("something went wrong");
            electionRepository.delete(election);
            var rescheduleRequest =modelMapper.map(request, ElectionRequest.class);
            scheduleElection(rescheduleRequest);
            return RescheduleElectionResponse.builder()
                    .rescheduledElectionId(request.getElectionId())
                    .timeRescheduled(now())
                    .rescheduledElectionTime(LocalDateTime.parse(request.getStartTime()))
                    .message("successfully rescheduled")
                    .build();
    }


    private boolean isValidTimeOfElection(LocalDateTime timeStarted,LocalDateTime timeEnded){
        return !timeStarted.isBefore(now())
                && !timeStarted.isAfter(timeEnded)
                && !timeEnded.isBefore(timeStarted)
                && !timeEnded.isBefore(now());

    }


    @Override
    public ViewElectionResultResponse viewElectionResult(ViewElectionResultRequest viewResult) {
        Election election = electionRepository.findElectionById(viewResult.getElectionId());
        if (election == null || election.getElectionStatus() == CONCLUDED)
            throw new ElectionNotFoundException("election not found");
        var result = ElectionResult.builder()
                .electionId(viewResult.getElectionId())
                .electionEndTime(election.getEndTime())
                .electionStartTime(election.getStartTime())
                .electionCategory(election.getElectionCategory())
                .electionStatus(election.getElectionStatus())
                .totalNumberOfVote(election.getTotalVote())
                .numberOfVotesReceived((long) election.winner().getNumberOfVotes())
                .winningCandidateInfo(Map.of("the winning candidate is " + election.winner().getUsername(),
                        CandidateInfo.builder()
                                .candidateName(election.winner().getUsername())
                                .candidateId(election.winner().getId())
                                .partyAffiliation(election.winner().getPartyAffiliation())
                                .build()))
                .build();
               var response=  modelMapper.map(result, ViewElectionResultResponse.class);
               response.setWinningCandidateInfo(result.getWinningCandidateInfo());
               return response;
    }


    }





