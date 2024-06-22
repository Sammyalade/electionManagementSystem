package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findVotesByCandidateId(Candidate candidateId);
}
