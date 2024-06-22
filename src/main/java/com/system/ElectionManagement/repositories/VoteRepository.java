package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.dtos.requests.GetCandidateVoteRequest;
import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.ElectionCategory;
import com.system.ElectionManagement.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT v FROM Vote v WHERE v.election = :category")
    List<Vote> findVotesByCategory(@Param("category") ElectionCategory electionCategory);

    @Query("SELECT v FROM Vote v WHERE v.voter.id = :voterId AND v.election.Id = :electionId")
    Optional<Vote> findByVoterAndElection(@Param("voterId") Long voterId, @Param("electionId") Long electionId);
    List<Vote> findVotesByCandidateId(Long id);
}
