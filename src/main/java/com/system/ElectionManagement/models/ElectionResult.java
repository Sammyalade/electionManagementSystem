package com.system.ElectionManagement.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "election_results")
public class ElectionResult {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long totalNumberOfVoteCasted;

    @OneToOne
    @JoinColumn(name = "election_id")
    private Election election;

    @Enumerated(value = STRING)
    private ElectionCategory electionCategory;
    @Enumerated(value = STRING)
    private ElectionStatus electionStatus;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    private Long numberOfVotesReceived;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime electionStartTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime electionEndTime;

}



