package com.system.ElectionManagement.dtos.requests;

import com.system.ElectionManagement.models.ElectionCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class RescheduleElectionRequest {
    private Long electionId;
    private String startTime;
    private String endTime;
    private ElectionCategory electionCategory;
    private Set<Long> candidateId;
    private String electionTitle;
}
