package com.system.ElectionManagement.dtos.responses;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class RescheduleElectionResponse {
    private String message;
    private String rescheduledElectionId;
    private LocalDateTime rescheduledElectionDate;
    private LocalDateTime rescheduledElectionTime;


}
