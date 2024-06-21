package com.system.ElectionManagement.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class RescheduleElectionRequest {
    private Long electionId;
    private LocalDateTime electionDate;
    private LocalDateTime electionTime;

}
