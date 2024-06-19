package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
