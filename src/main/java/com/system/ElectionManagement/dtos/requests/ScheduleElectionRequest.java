package com.system.ElectionManagement.dtos.requests;

import com.system.ElectionManagement.models.Election;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ScheduleElectionRequest {
    private Election election;
}
