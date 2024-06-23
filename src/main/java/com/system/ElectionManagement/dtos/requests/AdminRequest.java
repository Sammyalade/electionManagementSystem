package com.system.ElectionManagement.dtos.requests;

import com.system.ElectionManagement.dtos.requests.AddressRequest;
import com.system.ElectionManagement.dtos.requests.ContactInfoRequest;
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
