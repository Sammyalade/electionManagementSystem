package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.dtos.requests.GetCandidateVoteRequest;
import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.ElectionCategory;
import com.system.ElectionManagement.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findVotesByCandidateId(Long id);
    Vote findVoteById(Long id);
}
