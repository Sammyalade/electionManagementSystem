package com.system.ElectionManagement.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCandidateVoteRequest {
    private Long candidateId;
}
