package com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SystemAdministrator {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private AdminPrivilege adminPrivilege;
    private String username;
    private String password;
    @OneToOne
    private ContactInformation contactInformation;
}
