package com.system.ElectionManagement.dtos.requests;

import com.system.ElectionManagement.models.ElectionCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
public class GetAllVoteRequest {
    private ElectionCategory electionCategory;
}
