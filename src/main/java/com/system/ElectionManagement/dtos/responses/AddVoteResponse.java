package com.system.ElectionManagement.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.system.ElectionManagement.models.CandidateRequest;
import com.system.ElectionManagement.models.ElectionCategory;
import com.system.ElectionManagement.models.Voter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AddVoteResponse {
    private Long id;
    private Voter voter;
    private CandidateRequest candidate;
    private ElectionCategory election;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime timeVoted;
}
