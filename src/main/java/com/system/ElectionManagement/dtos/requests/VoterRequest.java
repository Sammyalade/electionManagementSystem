package com.system.ElectionManagement.dtos.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.models.EligibilityStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
public class VoterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private ContactInformation contactInformation;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    private EligibilityStatus eligibilityStatus;
}
