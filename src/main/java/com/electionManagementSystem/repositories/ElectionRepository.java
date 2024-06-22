package com.electionManagementSystem.repositories;

import com.electionManagementSystem.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Long> {
}
