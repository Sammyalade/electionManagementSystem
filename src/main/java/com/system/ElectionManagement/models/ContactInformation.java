package com.system.ElectionManagement.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class ContactInformation {
    @Id
    @GeneratedValue
    private Long id;
    private String phoneNumber;
    private String email;
    @OneToOne
    private Address address;
    private String stateOfOrigin;
    private String localGovernment;
    private String countryOfOrigin;
}
