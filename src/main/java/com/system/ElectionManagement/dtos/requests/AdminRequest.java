package com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminRequest {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private AddressRequest addressRequest;
    private ContactInfoRequest infoRequest;
}
