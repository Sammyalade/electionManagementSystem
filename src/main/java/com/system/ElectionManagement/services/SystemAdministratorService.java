package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.ScheduleElectionRequest;
import com.system.ElectionManagement.dtos.responses.ScheduleElectionResponse;

public interface SystemAdministratorService {


 ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElection);
}
