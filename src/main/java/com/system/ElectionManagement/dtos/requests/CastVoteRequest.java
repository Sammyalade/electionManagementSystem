package com.system.ElectionManagement.dtos.requests;

import com.system.ElectionManagement.models.ElectionCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CastVoteRequest {
    private Long id;
    private Long electionId;
    private Long candidateId;
}
