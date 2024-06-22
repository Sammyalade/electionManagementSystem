package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.CastVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetCandidateVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetCategoryVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetVoteRequest;
import com.system.ElectionManagement.dtos.responses.VoteResponse;
import com.system.ElectionManagement.exceptions.CastVoteException;
import com.system.ElectionManagement.repositories.CandidateRepository;
import com.system.ElectionManagement.services.impl.VoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.system.ElectionManagement.models.ElectionCategory.NATIONAL;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Sql(scripts = {"/db/data.sql"})
class VoteServicesTest {

    @Autowired
    private VoteServiceImpl voteService;
    @Autowired
    private CandidateRepository candidateRepository;
    @Test
    public void testToCastVote(){
        try {
            CastVoteRequest castVoteRequest = new CastVoteRequest();
            castVoteRequest.setId(1L);
            castVoteRequest.setElectionId(111L);
            castVoteRequest.setCandidateId(100L);
            var vote = voteService.castVote(castVoteRequest);
            assertThat(vote).isNotNull();
        }catch (Exception voteException){
            throw new CastVoteException("Vote Failed");
        }
    }
    @Test
    public void testToGetVote(){
        GetVoteRequest getVoteRequest = new GetVoteRequest();
        getVoteRequest.setVoteId(1L);
        Optional<VoteResponse> vote = voteService.getVoteById(getVoteRequest);
        assertThat(vote).isNotNull();
    }
    @Test
    public void testToGetAllVoteForCategory(){
        GetCategoryVoteRequest getCategoryVoteRequest = new GetCategoryVoteRequest();
        getCategoryVoteRequest.setElectionCategory(NATIONAL);
        List<VoteResponse> votes = voteService.getAllVoteForCategory(getCategoryVoteRequest);
        assertThat(votes).hasSize(1);
    }
    @Test
    public void testToGetAllVoteForCandidate(){
        GetCandidateVoteRequest getCandidateVoteRequest = new GetCandidateVoteRequest();
        getCandidateVoteRequest.setCandidateId(100L);
        List<VoteResponse> votes = voteService.getAllVoteForCandidate(getCandidateVoteRequest);
        assertThat(votes).hasSize(1);
    }

}