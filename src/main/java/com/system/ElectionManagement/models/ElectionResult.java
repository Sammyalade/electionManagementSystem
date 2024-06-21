package com.system.ElectionManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "election_results")
public class ElectionResult {
    @Id
    @GeneratedValue
    private long Id;
    private Long totalNumberOfVoteCasted;


}
