package com.system.ElectionManagement.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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


    private Set<Candidate> candidates;

    @Transient
    public int getTotalVote(){
        return candidates.stream()
        .mapToInt(Candidate::getNumberOfVotes).sum();
    }

    @Transient
    public  Candidate winner (){
        int largest = candidates.stream().findFirst().get().getNumberOfVotes();
        for(Candidate candidate : candidates){
            if(candidate.getNumberOfVotes() >largest){
                return candidate;
            }
        }
        return null;
    }
}
