package com.system.ElectionManagement.controllers;

import com.system.ElectionManagement.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping("/candidate/{id}")
    public ResponseEntity<?> getCandidates( @PathVariable("id") Long id){
        return ResponseEntity.ok(candidateService.findCandidateById(id));
    }
}
