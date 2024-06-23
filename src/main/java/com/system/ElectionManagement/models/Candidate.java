package com.system.ElectionManagement.models;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @OneToOne
    private ContactInformation contactInformation;
    private String partyAffiliation;
    private String biography;
    private String nominationFormUrl;
    private String financialDisclosureUrl;
    private String positionContested;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "candidate_votes",
            joinColumns = @JoinColumn(name = "canditate_id"),
            inverseJoinColumns = @JoinColumn(name = "voter_id")
    )
    private Set<Vote> votes;

    @Transient
    public int getNumberOfVotes(){
        return votes.size();
    }
}
