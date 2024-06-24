package com.system.ElectionManagement.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Election {
    @Id
    @GeneratedValue
    private Long id;
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
    private String electionTitle;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "registered_candidates",
            joinColumns = @JoinColumn(name = "election_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id")
    )


    private Set<CandidateRequest> candidates;

    @Transient
    public int getTotalVote(){
        return candidates.stream()
        .mapToInt(CandidateRequest::getNumberOfVotes).sum();
    }

    @Transient
    public CandidateRequest winner (){
        var largest = candidates.stream().mapToLong(CandidateRequest::getNumberOfVotes).max();
        return candidates.stream().filter(candidate -> (long) candidate.getNumberOfVotes() ==largest.getAsLong()).findFirst().get();
    }
}
