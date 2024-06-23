package com.system.ElectionManagement.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.ElectionCategory;
import com.system.ElectionManagement.models.ElectionResult;
import com.system.ElectionManagement.models.ElectionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Setter
@Getter
public class ViewElectionResultResponse {
    private Long electionId;
    private String electionTitle;
    private String candidateName;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endTime;
    private ElectionStatus electionStatus;
    private ElectionCategory electionCategory;
    private Long totalNumberOfVotesCasted;
    private Long numberOfVotesReceived;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using  = LocalDateDeserializer.class)
    private LocalDate electionDate;

}
