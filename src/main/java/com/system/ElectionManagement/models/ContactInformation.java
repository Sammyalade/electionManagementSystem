package com.system.ElectionManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ContactInformation {
    @Id
    @GeneratedValue
    private int id;
    private String phoneNumber;
    private String email;
    @OneToOne
    private Address address;
    private String stateOfOrigin;
    private String localGovernment;
    private String countryOfOrigin;
}
