package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
        Candidate findCandidateById(Long id);

        Candidate findCandidateByUsernameIgnoreCase(String username);
}
