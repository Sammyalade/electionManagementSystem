package com.system.ElectionManagement.dtos.requests;

import com.system.ElectionManagement.models.ElectionCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryVoteRequest {
    private ElectionCategory electionCategory;
}
