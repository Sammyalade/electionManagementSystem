package com.system.ElectionManagement.dtos.requests;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LogInRequest {
    @Id
    private Long id;
    private String username;
    private String password;
}
