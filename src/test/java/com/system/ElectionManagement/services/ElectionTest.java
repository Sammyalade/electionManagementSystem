package com.system.ElectionManagement.services;



import com.system.ElectionManagement.dtos.requests.RescheduleElectionRequest;
import com.system.ElectionManagement.dtos.responses.RescheduleElectionResponse;
import com.system.ElectionManagement.exceptions.CantRescheduleElectionException;
import com.system.ElectionManagement.exceptions.ElectionNotFoundException;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.ElectionStatus;
import com.system.ElectionManagement.repositories.ElectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class ElectionTest {
    @Autowired
    ElectionService electionService;
    @Autowired
    ElectionRepository electionRepository;

    @Test
    public void testRescheduleElection() {
        Optional<Election> optionalElection = electionRepository.findById(300L);
        Election existingElection = optionalElection.orElseThrow(() -> new ElectionNotFoundException("Election not found"));

        assertEquals(ElectionStatus.SCHEDULED, existingElection.getElectionStatus());

        RescheduleElectionRequest request = new RescheduleElectionRequest();
        request.setElectionId(Long.valueOf(String.valueOf(existingElection.getId())));
        LocalDateTime newStartTime = LocalDateTime.of(2025, 7, 1, 10, 0);
        request.setElectionDate(newStartTime);
        RescheduleElectionResponse response = electionService.rescheduleElection(request);
        assertNotNull(response);
        assertEquals("Election rescheduled successfully", response.getMessage());
        assertEquals(String.valueOf(existingElection.getId()), response.getRescheduledElectionId());
        assertEquals(newStartTime, response.getRescheduledElectionDate());
        Optional<Election> updatedOptional = electionRepository.findById(existingElection.getId());
        Election updatedElection = updatedOptional.orElseThrow(() -> new ElectionNotFoundException("Updated election not found"));

        assertEquals(newStartTime, updatedElection.getStartTime());
        assertEquals(ElectionStatus.SCHEDULED, updatedElection.getElectionStatus());

    }
    @Test
    public void testRescheduleElection_InvalidDate() {
        Optional<Election> optionalElection = electionRepository.findById(300L);
        Election existingElection = optionalElection.orElseThrow(() -> new ElectionNotFoundException("Election not found"));

        RescheduleElectionRequest request = new RescheduleElectionRequest();
        request.setElectionId(Long.valueOf(String.valueOf(existingElection.getId())));
        request.setElectionDate(null);

        Exception exception = assertThrows(ElectionNotFoundException.class, () -> {
            electionService.rescheduleElection(request);
        });

        assertEquals("Invalid date", exception.getMessage());
    }
    @Test
    public void testRescheduleElection_AlreadyStarted() {
        Optional<Election> optionalElection = electionRepository.findById(300L);
        Election existingElection = optionalElection.orElseThrow(() -> new ElectionNotFoundException("Election not found"));
        existingElection.setElectionStatus(ElectionStatus.IN_PROGRESS);
        electionRepository.save(existingElection);

        RescheduleElectionRequest request = new RescheduleElectionRequest();
        request.setElectionId(Long.valueOf(String.valueOf(existingElection.getId())));
        request.setElectionDate(LocalDateTime.of(2024, 7, 1, 10, 0));

        Exception exception = assertThrows(CantRescheduleElectionException.class, () -> {
            electionService.rescheduleElection(request);
        });

        assertEquals("Cannot reschedule an ongoing or completed election", exception.getMessage()); 
    }
    @Test
    public void testRescheduleElection_InvalidElectionId() {
        RescheduleElectionRequest request = new RescheduleElectionRequest();
        request.setElectionId(999L);
        request.setElectionDate(LocalDateTime.of(2024, 7, 1, 10, 0));

        Exception exception = assertThrows(ElectionNotFoundException.class, () -> {
            electionService.rescheduleElection(request);
        });

        assertEquals("Election not found", exception.getMessage());
    }




}

