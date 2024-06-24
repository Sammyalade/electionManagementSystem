package com.system.ElectionManagement.controllers;

import com.system.ElectionManagement.dtos.requests.AdminRequest;
import com.system.ElectionManagement.dtos.requests.VoteRequest;
import com.system.ElectionManagement.services.AdminService;
import com.system.ElectionManagement.services.VoteService;
import com.system.ElectionManagement.services.VoterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/vote")
@AllArgsConstructor
public class VoteController {
    private final VoteService voteService;
    @PostMapping("/cast-vote")
    public ResponseEntity<?> castVote(@RequestBody VoteRequest request) {
        return ResponseEntity.status(CREATED).body(voteService.castBallot(request));
    }
}
