package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
