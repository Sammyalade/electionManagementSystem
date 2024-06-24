package com.system.ElectionManagement.controllers;
import com.system.ElectionManagement.services.AdminService;
import com.system.ElectionManagement.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidate")
public class CandidateController {
    @Autowired
    AdminService adminService;
    @Autowired
    CandidateService candidateService;


    @GetMapping
    public ResponseEntity<?> getCandidates(Long id) {
        return ResponseEntity.ok(candidateService.findCandidateById(id));
    }
}
