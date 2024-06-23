package com.system.ElectionManagement.dtos.responses;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class RescheduleElectionResponse {
    private String message;
    private Long rescheduledElectionId;
    private LocalDateTime timeRescheduled;
    private LocalDateTime rescheduledElectionTime;
}
