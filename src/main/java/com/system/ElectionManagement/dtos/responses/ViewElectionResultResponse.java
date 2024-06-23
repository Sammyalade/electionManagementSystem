package com.system.ElectionManagement.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.system.ElectionManagement.dtos.CandidateInfo;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.ElectionCategory;
import com.system.ElectionManagement.models.ElectionResult;
import com.system.ElectionManagement.models.ElectionStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;


@Data
public class ViewElectionResultResponse {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Integer totalNumberOfVote;
    private Long electionId;
    private ElectionCategory electionCategory;
    private ElectionStatus electionStatus;
    private Long numberOfVotesReceived;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime electionStartTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime electionEndTime;
    private Map<String, CandidateInfo> winningCandidateInfo;

}
