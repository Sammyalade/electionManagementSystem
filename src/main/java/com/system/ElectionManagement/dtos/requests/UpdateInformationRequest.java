package com.system.ElectionManagement.dtos.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UpdateInformationRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

}
