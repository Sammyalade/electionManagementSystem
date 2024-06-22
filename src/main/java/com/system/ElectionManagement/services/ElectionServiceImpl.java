package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.dtos.requests.RescheduleElectionRequest;
import com.system.ElectionManagement.dtos.responses.ElectionResponse;
import com.system.ElectionManagement.dtos.responses.RescheduleElectionResponse;
import com.system.ElectionManagement.exceptions.CantRescheduleElectionException;
import com.system.ElectionManagement.exceptions.ElectionNotFoundException;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.ElectionStatus;
import com.system.ElectionManagement.repositories.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ElectionServiceImpl implements ElectionService {

    private final ElectionRepository electionRepository;


    @Autowired
    public ElectionServiceImpl(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;

    }

    @Override
    public RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest rescheduleRequest) {
        RescheduleElectionResponse response = new RescheduleElectionResponse();

        Long electionId = Long.parseLong(String.valueOf(rescheduleRequest.getElectionId()));
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new ElectionNotFoundException("Election not found"));

        if (election.getElectionStatus() == ElectionStatus.IN_PROGRESS || election.getElectionStatus() == ElectionStatus.CONCLUDED) {
            throw new CantRescheduleElectionException("Cannot reschedule an ongoing or completed election");
        }

        if (rescheduleRequest.getElectionDate() == null) {
            throw new ElectionNotFoundException("Invalid date");
        }

        election.setStartTime(rescheduleRequest.getElectionDate());
        election.setElectionStatus(ElectionStatus.NOT_STARTED);
        Election updatedElection = electionRepository.save(election);

        response.setMessage("Election rescheduled successfully");
        response.setRescheduledElectionId(String.valueOf(updatedElection.getId()));
        response.setRescheduledElectionDate(updatedElection.getStartTime());
        return response;
    }

    @Override
    public ElectionResponse scheduleElection(ElectionRequest electionRequest) {
        return null;
    }
}