package com.system.ElectionManagement.dtos.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.Vote;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest {
    private Long id;
    private String username;
    private String password;
    private ContactInformation contactInformation;
    private String partyAffiliation;
    private String biography;
    private MultipartFile nominationForm;
    private MultipartFile financialDisclosureForm;
    private String positionContested;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    private Long electionId;
    private Set<Vote> votes;
}
