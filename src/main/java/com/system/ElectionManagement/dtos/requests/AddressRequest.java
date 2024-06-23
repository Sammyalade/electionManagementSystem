package com.system.ElectionManagement.dtos.requests;


import lombok.Data;

@Data
public class AddressRequest {
    private String streetName;
    private String city;
    private String zipCode;
    private String postalCode;
}
