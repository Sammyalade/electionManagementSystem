package com.system.ElectionManagement.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateInfo {
    private String partyAffiliation;
    private String candidateName;
    private Long candidateId;
}
