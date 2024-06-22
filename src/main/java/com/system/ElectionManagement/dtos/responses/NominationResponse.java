package com.system.ElectionManagement.dtos.responses;

import com.system.ElectionManagement.models.ElectionCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class NominationResponse {
    private Long id;
    private String positionContested;
    private LocalDateTime dateApproved;
    private LocalDateTime dateSubmitted;
    private ElectionCategory electionCategory;


}
