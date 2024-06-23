package com.system.ElectionManagement.dtos.requests;


import com.system.ElectionManagement.models.Address;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactInfoRequest {
    private String phoneNumber;
    private String email;
    private AddressRequest address;
    private String stateOfOrigin;
    private String localGovernment;
    private String countryOfOrigin;
}
