package com.electionManagementSystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class SystemAdministrator {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private AdminPrivilege adminPrivilege;
    private String username;
    private String password;
    @OneToOne
    private ContactInformation contactInformation;
}
