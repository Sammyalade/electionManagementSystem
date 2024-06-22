package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ElectionRepository extends JpaRepository<Election, Long> {
    Election findElectionById(Long electionId);

}

