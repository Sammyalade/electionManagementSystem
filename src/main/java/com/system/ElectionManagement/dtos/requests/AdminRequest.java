package com.electionManagementSystem.dtos.requests;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminRequest {
    private String username;
    private String password;
}
