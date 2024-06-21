package com.system.ElectionManagement.dtos.responses;

import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.ElectionCategory;
import com.system.ElectionManagement.models.ElectionStatus;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectionResponse {
    private Long id;
    private String startTime;
    private String endTime;
    private ElectionStatus electionStatus;
    private ElectionCategory electionCategory;
    private Set<Candidate> candidates;

    private String electionTitle;
}


