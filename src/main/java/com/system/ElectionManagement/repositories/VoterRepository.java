package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    Voter findVoterById(Long id);

    Voter findVoterByUsername(String username);
}
