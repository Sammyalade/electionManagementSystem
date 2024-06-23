package com.system.ElectionManagement.dtos.requests;


import com.system.ElectionManagement.models.AdminPrivilege;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AdminRequest {
    private String firstName;
    private String lastName;
    private AdminPrivilege privilege;
    private String username;
    private String password;
}
