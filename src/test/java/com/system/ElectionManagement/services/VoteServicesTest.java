package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.AddVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetAllVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetVoteRequest;
import com.system.ElectionManagement.dtos.responses.VoteResponse;
import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.repositories.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static com.system.ElectionManagement.models.ElectionCategory.NATIONAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
class VoteServicesTest {

    @Autowired
    private VoteServiceImpl voteService;
    @Autowired
    private CandidateRepository candidateRepository;
    @Test
    public void testToAddVote(){
        AddVoteRequest addVoteRequest = new AddVoteRequest();
        addVoteRequest.setId(1L);
        addVoteRequest.setElection(NATIONAL);
        addVoteRequest.setCandidateId(100L);
        var vote = voteService.addVote(addVoteRequest);
        assertThat(vote).isNotNull();
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
        GetAllVoteRequest getAllVoteRequest = new GetAllVoteRequest();
        getAllVoteRequest.setElectionCategory(NATIONAL);
        List<VoteResponse> votes = voteService.getAllVoteForCategory(getAllVoteRequest);
        assertThat(votes).hasSize(1);
    }

}