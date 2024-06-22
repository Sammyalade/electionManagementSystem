package com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ElectionRequest {
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String category;
    private Long adminId;
}
