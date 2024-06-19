package com.electionManagementSystem.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Candidate {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    @OneToOne
    private ContactInformation contactInformation;
    private String partyAffiliation;
    private String biography;
    @OneToOne
    private NominationForm nominationForm;
    @OneToOne
    private ElectionResult result;
    private String positionContested;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateOfBirth;

}
