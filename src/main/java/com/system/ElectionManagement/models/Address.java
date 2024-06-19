package com.system.ElectionManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue
    private int id;
    private String streetName;
    private String city;
    private int zipCode;
    private Long postalCode;
}
