package com.system.ElectionManagement.controllers;

import com.system.ElectionManagement.dtos.requests.CandidateRequest;
import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.dtos.requests.ViewElectionResultRequest;
import com.system.ElectionManagement.dtos.responses.CandidateResponse;
import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.services.AdminService;
import com.system.ElectionManagement.services.CandidateService;
import com.system.ElectionManagement.services.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/election")

public class    ElectionController {
    @Autowired
    AdminService adminService;
    @Autowired
    ElectionService electionService;
    @Autowired
    CandidateService candidateService;

    @GetMapping
    public ResponseEntity<?> findElection(@RequestParam long candidateId) {
        return ResponseEntity.ok(electionService.findElectionByCandidateId(candidateId));
    }

    @GetMapping
    public ResponseEntity<?> getResult(@RequestBody ViewElectionResultRequest request) {
        return ResponseEntity.ok(electionService.viewElectionResult(request));
    }
}



