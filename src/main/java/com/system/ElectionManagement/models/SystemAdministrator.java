package com.system.ElectionManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemAdministrator {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private AdminPrivilege adminPrivilege;
    private String username;
    private String password;
    @OneToOne
    private ContactInformation contactInformation;
}
