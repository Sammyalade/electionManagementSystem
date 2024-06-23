package com.system.ElectionManagement.dtos.responses;

import com.system.ElectionManagement.models.AdminPrivilege;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminResponse {
    private long id;
    private String firstName;
    private String lastName;
    private AdminPrivilege privilege;
    private String username;
    private String password;
}
