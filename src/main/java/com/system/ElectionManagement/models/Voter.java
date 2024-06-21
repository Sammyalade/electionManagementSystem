package com.system.ElectionManagement.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Voter {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    @OneToOne
    private ContactInformation contactInformation;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private EligibilityStatus eligibilityStatus;
}
