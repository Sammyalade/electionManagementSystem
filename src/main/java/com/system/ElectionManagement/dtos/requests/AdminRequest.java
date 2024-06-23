package com.system.ElectionManagement.dtos.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AdminRequest {
    private String username;
    private String password;
}
