package com.system.ElectionManagement.dtos.requests;

import com.system.ElectionManagement.models.ElectionCategory;
import com.system.ElectionManagement.models.NominationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NominationRequest {
    private Long id;
    private String positionContested;
    private LocalDateTime dateApproved;
    private LocalDateTime dateSubmitted;
    private NominationStatus status;
    private ElectionCategory category;


}
