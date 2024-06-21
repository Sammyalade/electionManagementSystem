package com.system.ElectionManagement.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;

@Data
@Entity
public class Election {
    @Id
    @GeneratedValue
    private long id;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endTime;
    @Enumerated(value = STRING)
    private ElectionStatus electionStatus;
    @Enumerated(value = STRING)
    private ElectionCategory electionCategory;
    @OneToOne
    private ElectionResult electionResult;
    @ManyToMany
    @JoinTable(
            name = "voter_election",
            joinColumns = @JoinColumn(name = "election_id"),
            inverseJoinColumns = @JoinColumn(name = "voter_id")
    )
    private Set<Voter> voters;
}
