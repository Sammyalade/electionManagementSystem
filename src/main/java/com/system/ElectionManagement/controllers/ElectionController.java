package com.system.ElectionManagement.controllers;

import com.system.ElectionManagement.dtos.requests.ViewElectionResultRequest;
import com.system.ElectionManagement.services.ElectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class ElectionController {
    private final ElectionService electionService;

    @GetMapping
    public ResponseEntity<?> findElection(@RequestParam long candidateId) {
        return ResponseEntity.ok(electionService.findElectionByCandidateId(candidateId));
    }

    @GetMapping
    public ResponseEntity<?> getResult(@RequestBody ViewElectionResultRequest request) {
        return ResponseEntity.ok(electionService.viewElectionResult(request));
    }
}
