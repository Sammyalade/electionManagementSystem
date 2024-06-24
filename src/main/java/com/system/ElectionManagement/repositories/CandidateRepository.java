package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.CandidateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateRequest, Long> {
        CandidateRequest findCandidateById(Long id);

        CandidateRequest findCandidateByUsernameIgnoreCase(String username);
}
