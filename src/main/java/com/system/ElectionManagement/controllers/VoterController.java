package com.system.ElectionManagement.controllers;

import com.cloudinary.api.ApiResponse;
import com.system.ElectionManagement.dtos.requests.UpdateRequest;
import com.system.ElectionManagement.dtos.requests.VoterRequest;
import com.system.ElectionManagement.services.VoterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/voter")
@AllArgsConstructor
public class VoterController {
    private final VoterService voterService;
    @PatchMapping
    public ResponseEntity<?> updateVoter(@RequestBody UpdateRequest voter) {
        return ResponseEntity.status(CREATED).body(voterService.updateVoterInfo(voter));
    }
}
