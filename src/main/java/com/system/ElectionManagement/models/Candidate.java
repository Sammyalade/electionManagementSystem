package com.system.ElectionManagement.models;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
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
    @OneToMany
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
