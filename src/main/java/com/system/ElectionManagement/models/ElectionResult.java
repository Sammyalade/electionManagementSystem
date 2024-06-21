package com.system.ElectionManagement.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Data;

import javax.print.DocFlavor;
import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@Data
@Entity
@Table(name = "election_results")
public class ElectionResult {
    @Id
    @GeneratedValue
    private Long Id;
    private Long totalNumberOfVoteCasted;

    @OneToOne
    private Election election;

    @Enumerated(value = STRING)
    private ElectionCategory electionCategory;
    @Enumerated(value = STRING)
    private ElectionStatus electionStatus;

    @OneToOne
    private Candidate candidate;
    private Long numberOfVotesReceived;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate electionDate;
    private Long totalNumberOfVotesCasted;
}



