package com.system.ElectionManagement.controllers;

import com.system.ElectionManagement.dtos.requests.VoteRequest;
import com.system.ElectionManagement.services.AdminService;
import com.system.ElectionManagement.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/vote")
public class VoteController {
    @Autowired
    AdminService adminService;
    @Autowired
    VoteService voteService;

    @PostMapping
    public ResponseEntity<?> castVote(@RequestBody VoteRequest request) {
        return ResponseEntity.status(CREATED).body(voteService.castBallot(request));
    }
}


