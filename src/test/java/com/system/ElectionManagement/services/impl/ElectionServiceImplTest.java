package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.models.Candidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Set;

import static com.system.ElectionManagement.models.ElectionCategory.LOCAL_GOVERNMENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ElectionServiceImplTest {

    
    @Autowired
    private ElectionServiceImpl electionService;
    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testThatElectionCanBeScheduled() {
        var request = ElectionRequest.builder()
                .candidateId(Set.of(1L))
                .startTime("2024-07-20T00:00:00.0000")
                .endTime("2024-07-20T00:00:00.0000")
                .electionCategory(LOCAL_GOVERNMENT)
                .electionTitle("millionaire")
                .build();
         var response = electionService.scheduleElection(request);
         assertThat(response).isNotNull();
         assertThat(response.getCandidates().size()).isEqualTo(1);
         assertThat(response.getCandidates().contains(null)).isFalse();
    }

    @Test
    void testThatElectionTimeIsInvalid_throwsAnException(){
        var request = ElectionRequest.builder()
                .candidateId(Set.of(1L,2L))
                .startTime("2024-05-20T00:00:00.0000")
                .endTime("2024-02-20T00:00:00.0000")
                .electionCategory(LOCAL_GOVERNMENT)
                .electionTitle("millionaire")
                .build();
        assertThrows(RuntimeException.class, () ->electionService.scheduleElection(request));
    }


    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testThatWeCanFindElectionByCandidateId(){
        var request = ElectionRequest.builder()
                .candidateId(Set.of(1L))
                .startTime("2024-07-20T00:00:00.0000")
                .endTime("2024-07-20T00:00:00.0000")
                .electionCategory(LOCAL_GOVERNMENT)
                .electionTitle("millionaire")
                .build();
        var response = electionService.scheduleElection(request);
        var election =electionService.findElectionByCandidateId(1L);
        assertThat(election.getCandidates().stream().map(Candidate::getId)).contains(1L);
    }

}