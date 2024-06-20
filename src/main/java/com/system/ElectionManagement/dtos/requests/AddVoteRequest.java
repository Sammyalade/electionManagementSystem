package com.system.ElectionManagement.dtos.requests;

import com.system.ElectionManagement.models.ElectionCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddVoteRequest {
    private Long id;
    private ElectionCategory election;
}
