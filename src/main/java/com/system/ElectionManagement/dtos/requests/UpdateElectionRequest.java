package com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateElectionRequest {
    private Long electionId;
    private String adminUsername;
    private String adminPassword;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String category;
}
