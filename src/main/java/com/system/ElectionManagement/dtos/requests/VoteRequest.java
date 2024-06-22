package com.system.ElectionManagement.dtos.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.models.EligibilityStatus;
import com.system.ElectionManagement.models.Voter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Setter
@Getter
public class VoteRequest {
    private Long voterId;
    private Long candidateId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime timeVoted;
}
