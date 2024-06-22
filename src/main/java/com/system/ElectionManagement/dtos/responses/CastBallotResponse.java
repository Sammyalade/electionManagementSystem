package com.system.ElectionManagement.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CastBallotResponse {
    private String message;
    private int numberOfVote;
}
