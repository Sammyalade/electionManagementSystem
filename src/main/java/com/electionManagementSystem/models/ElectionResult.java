package com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "election_results")
public class ElectionResult {
    @Id
    @GeneratedValue
    private long Id;
    private Long totalNumberOfVoteCasted;

}
