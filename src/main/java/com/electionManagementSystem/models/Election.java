package com.electionManagementSystem.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;

@Data
@Entity
@Table(name="elections")
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDate;
    @Enumerated(value = STRING)
    private ElectionStatus electionStatus;
    @Enumerated(value = STRING)
    private ElectionCategory electionCategory;
    @OneToOne
    private ElectionResult electionResult;
    @ManyToMany
    @JoinTable(name = "cadidate_election", joinColumns = @JoinColumn(name = "election_id"), inverseJoinColumns = @JoinColumn(name = "candidates_id"))
    private Set<Candidate> candidates;
}
