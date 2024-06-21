package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.ScheduleElectionRequest;
import com.system.ElectionManagement.dtos.responses.ScheduleElectionResponse;
import com.system.ElectionManagement.repositories.ElectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SystemAdministratorImpl implements SystemAdministratorService{
    private final ElectionRepository electionRepository;




    @Override
    public ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElection) {
        return null;
    }
}
