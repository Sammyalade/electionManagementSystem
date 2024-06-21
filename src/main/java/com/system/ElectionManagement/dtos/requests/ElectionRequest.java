package com.system.ElectionManagement.dtos.requests;
import com.system.ElectionManagement.models.ElectionCategory;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Set;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectionRequest {
    private String startTime;
    private String endTime;
    private ElectionCategory electionCategory;
    private Set<Long> candidateId;

    private String electionTitle;
}
