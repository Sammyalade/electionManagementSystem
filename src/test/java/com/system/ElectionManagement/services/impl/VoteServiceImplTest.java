package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.VoteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest


class VoteServiceImplTest {
@Autowired
private VoteServiceImpl voteService;
@Autowired
private ElectionServiceImpl electionService;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testThatVoterCanVote_andCandidatesCanBeVotedFor() {
        VoteRequest voteRequest =  new VoteRequest();
        voteRequest.setCandidateId(1L);
        voteRequest.setVoterId(1L);
        voteService.castBallot(voteRequest);
       var candidateWithVote= electionService.findElectionByCandidateId(1L);
        assertThat(candidateWithVote.getTotalVote()).isEqualTo(1);
    }
    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testThatVoterCanNotVoteForTheSameCandidateTwice() {
        VoteRequest voteRequest =  new VoteRequest();
        voteRequest.setCandidateId(1L);
        voteRequest.setVoterId(1L);
        voteService.castBallot(voteRequest);
        var candidateWithVote= electionService.findElectionByCandidateId(1L);
        assertThrows(RuntimeException.class,()->   voteService.castBallot(voteRequest));
    }


}