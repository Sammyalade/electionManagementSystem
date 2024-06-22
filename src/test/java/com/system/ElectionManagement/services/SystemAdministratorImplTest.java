package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.ScheduleElectionRequest;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.ElectionStatus;
import com.system.ElectionManagement.models.Voter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.system.ElectionManagement.models.ElectionCategory.NATIONAL;


@SpringBootTest
public class SystemAdministratorImplTest {
    @Autowired
    private SystemAdministratorService adminService;

    @Test
    public void scheduleElectionTest(){
        Election election = new Election();
        Voter voter1 = new Voter();
        Voter voter2 = new Voter();
        Set<Voter> voters = new HashSet<>(Arrays.asList(voter1, voter2));
        election.setId(1L);
        election.setStartTime(LocalDateTime.of(2024, 11, 5, 8, 0));
        election.setEndTime(LocalDateTime.of(2024, 11, 5, 18, 0));
        election.setElectionCategory(NATIONAL);
        election.setElectionStatus(ElectionStatus.SCHEDULED);

        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setElection(election);

    }
}
