package com.system.ElectionManagement.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.system.ElectionManagement.dtos.CandidateInfo;
import com.system.ElectionManagement.dtos.responses.CandidateResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "election_results")
public class ElectionResult {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Integer totalNumberOfVote;
    private Long electionId;
    @Enumerated(value = STRING)
    private ElectionCategory electionCategory;
    @Enumerated(value = STRING)
    private ElectionStatus electionStatus;
    private Long numberOfVotesReceived;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime electionStartTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime electionEndTime;
    @Transient
    private Map<String, CandidateInfo> winningCandidateInfo;

}



