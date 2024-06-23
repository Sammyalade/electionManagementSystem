package com.system.ElectionManagement.controllers;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @PostMapping
    public ResponseEntity<?> addAdmin(@RequestBody AdminRequest request) {
        return ResponseEntity.status(CREATED).body(adminService.registerAsAdmin(request));
    }
    @PostMapping
    public ResponseEntity<?> registerCandidate(@RequestBody CandidateRequest request) {
        return ResponseEntity.status(CREATED).body(adminService.registerCandidate(request));
    }
    @PostMapping
    public ResponseEntity<?> registerVoter(@RequestBody VoterRequest request) {
        return ResponseEntity.status(CREATED).body(adminService.registerVoter(request));
    }
    @PostMapping
    public ResponseEntity<?> scheduleElection(@RequestBody ElectionRequest request) {
        return ResponseEntity.status(CREATED).body(adminService.scheduleElection(request));
    }
    @PostMapping
    public ResponseEntity<?> reScheduleCandidate(@RequestBody RescheduleElectionRequest request) {
        return ResponseEntity.status(CREATED).body(adminService.rescheduleElection(request));
    }
}
