package com.system.ElectionManagement.services;

import com.system.ElectionManagement.dtos.requests.AddVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetAllVoteRequest;
import com.system.ElectionManagement.dtos.requests.GetVoteRequest;
import com.system.ElectionManagement.dtos.responses.VoteResponse;
import com.system.ElectionManagement.models.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.system.ElectionManagement.models.ElectionCategory.NATIONAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VoteServicesTest {

    @Autowired
    private VoteServiceImpl voteService;
    @Test
    public void testToAddVote(){
        AddVoteRequest addVoteRequest = new AddVoteRequest();
        addVoteRequest.setId(1L);
        addVoteRequest.setElection(NATIONAL);
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
    public void testToGetAllVote(){
        GetAllVoteRequest getAllVoteRequest = new GetAllVoteRequest();
        getAllVoteRequest.setElectionCategory(NATIONAL);
        List<VoteResponse> votes = voteService.getAllVote(getAllVoteRequest);
        assertThat(votes).hasSize(1);
    }
}