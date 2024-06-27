package com.system.ElectionManagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.models.Address;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.services.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/add-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminRequest request) {
        return ResponseEntity.status(CREATED).body(adminService.registerAsAdmin(request));
    }

    @PostMapping(value = "/register/candidate", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> registerCandidate( @ModelAttribute CandidateRequest candidateRequest) {
        log.info("candidateRequest: {}", candidateRequest);
        return ResponseEntity.status(CREATED).body(adminService.registerCandidate(candidateRequest));
    }
    @PostMapping("/register/voter")
    public ResponseEntity<?> registerVoter(@RequestBody VoterRequest request) {
        return ResponseEntity.status(CREATED).body(adminService.registerVoter(request));
    }
    @PostMapping("/schedule/election")
    public ResponseEntity<?> scheduleElection(@RequestBody ElectionRequest request) {
        return ResponseEntity.status(CREATED).body(adminService.scheduleElection(request));
    }
    @PostMapping("/reschedule/election")
    public ResponseEntity<?> reScheduleCandidate(@RequestBody RescheduleElectionRequest request) {
        return ResponseEntity.status(CREATED).body(adminService.rescheduleElection(request));
    }
}
