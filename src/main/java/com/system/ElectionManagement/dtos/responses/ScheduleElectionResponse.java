package com.system.ElectionManagement.dtos.responses;

import com.system.ElectionManagement.models.Election;
import lombok.Data;

@Data
public class ScheduleElectionResponse {
    private Election electionSchedule;
}
