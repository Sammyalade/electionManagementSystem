package com.electionManagementSystem.models;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
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
    private String username;
    private String pssword;
    @OneToOne
    private ElectionResult result;
    @ManyToOne
    private Election election;
    private String positionContested;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    @OneToMany
    @JoinTable(name="candidate_votes", joinColumns=@JoinColumn(name="candidate_id"),
                inverseJoinColumns = @JoinColumn(name="vote_id"))
    private Set<Vote> votes;
}
