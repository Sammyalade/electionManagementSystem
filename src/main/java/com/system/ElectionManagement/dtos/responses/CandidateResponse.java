package com.system.ElectionManagement.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.Vote;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CandidateResponse {
    private Long id;
    private String username;
    private String password;
    private ContactInformation contactInformation;
    private String partyAffiliation;
    private String biography;
    private String nominationFormUrl;
    private String financialDisclosureFormUrl;
    private String positionContested;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    private Election election;
    private Set<Vote> votes;
}
