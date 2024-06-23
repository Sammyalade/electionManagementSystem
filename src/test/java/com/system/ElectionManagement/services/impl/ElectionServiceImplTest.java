package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.*;
import com.system.ElectionManagement.dtos.responses.RescheduleElectionResponse;
import com.system.ElectionManagement.dtos.responses.ViewElectionResultResponse;
import com.system.ElectionManagement.models.*;
import com.system.ElectionManagement.repositories.ElectionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDateTime;
import java.util.Set;

import static com.system.ElectionManagement.models.ElectionCategory.LOCAL_GOVERNMENT;
import static com.system.ElectionManagement.models.ElectionCategory.PRESIDENTIAL;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ElectionServiceImplTest {


    @Autowired
    private ElectionServiceImpl electionService;
    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private CandidateServiceImpl candidateService;
    @Autowired
    private VoteServiceImpl voteService;

    @Test
    @Sql(scripts = {"/db/newData.sql"})
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
    void testThatElectionTimeIsInvalid_throwsAnException() {
        var request = ElectionRequest.builder()
                .candidateId(Set.of(1L, 2L))
                .startTime("2024-05-20T00:00:00.0000")
                .endTime("2024-02-20T00:00:00.0000")
                .electionCategory(LOCAL_GOVERNMENT)
                .electionTitle("millionaire")
                .build();
        assertThrows(RuntimeException.class, () -> electionService.scheduleElection(request));
    }


    @Test
    @Sql(scripts = {"/db/newData.sql"})
    void testThatWeCanFindElectionByCandidateId() {
        var request = ElectionRequest.builder()
                .candidateId(Set.of(1L))
                .startTime("2024-07-20T00:00:00.0000")
                .endTime("2024-07-20T00:00:00.0000")
                .electionCategory(LOCAL_GOVERNMENT)
                .electionTitle("millionaire")
                .build();
        var response = electionService.scheduleElection(request);
        var election = electionService.findElectionByCandidateId(1L);
        assertThat(election.getCandidates().stream().map(Candidate::getId)).contains(1L);
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    @DisplayName("test that to view election results")
    public void testViewElectionResult() {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setVoterId(1L);
        voteRequest.setCandidateId(1L);
        voteRequest.setTimeVoted(now());
        voteService.castBallot(voteRequest);
        voteRequest.setVoterId(2L);
        voteRequest.setCandidateId(1L);
        voteRequest.setTimeVoted(now());
        voteService.castBallot(voteRequest);
        voteRequest.setVoterId(3L);
        voteRequest.setCandidateId(2L);
        voteRequest.setTimeVoted(now());
        voteService.castBallot(voteRequest);
        ViewElectionResultRequest viewResult = new ViewElectionResultRequest();
        viewResult.setElectionId(1L);
        ViewElectionResultResponse resultResponse = electionService.viewElectionResult(viewResult);
        assertThat(electionRepository.findElectionById(1L).winner().getId()).isEqualTo(1L);
      assertThat(electionRepository.findElectionById(1L).winner().getNumberOfVotes()).isEqualTo(2);
    }

    private static ElectionResult getElectionResult(Election election, Candidate candidate) {
        ElectionResult electionResult = new ElectionResult();
        electionResult.setElectionCategory(PRESIDENTIAL);
        electionResult.setElectionStatus(ElectionStatus.CONCLUDED);

        electionResult.setNumberOfVotesReceived(600L);
        electionResult.setElectionStartTime(LocalDateTime.of(2024, 6, 1, 8, 0));
        electionResult.setElectionEndTime(LocalDateTime.of(2024, 6, 1, 20, 0));
        return electionResult;
    }

    private static Candidate getCandidate() {
        Candidate candidate = new Candidate();
        candidate.setFirstName("Bola");
        candidate.setLastName("Tinubu");
        return candidate;
    }

    private static Election getElection() {
        Election election = new Election();
        election.setElectionTitle("Presidential Election");
        election.setStartTime(LocalDateTime.of(2024, 6, 1, 8, 0));
        election.setEndTime(LocalDateTime.of(2024, 6, 1, 20, 0));
        election.setElectionStatus(ElectionStatus.CONCLUDED);
        election.setElectionCategory(PRESIDENTIAL);
        return election;
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testRescheduleElection() {
        assertThat(electionRepository.findElectionById(1L).getElectionCategory()).isEqualTo(PRESIDENTIAL);
        var request =RescheduleElectionRequest.builder()
                .candidateId(Set.of(1L))
                .startTime("2024-07-20T00:00:00.0000")
                .endTime("2024-07-20T00:00:00.0000")
                .electionCategory(LOCAL_GOVERNMENT)
                .electionTitle("millionaire")
                .electionId(1L)
                .build();
        RescheduleElectionResponse response = electionService.rescheduleElection(request);
        assertNotNull(response);
        assertEquals("successfully rescheduled", response.getMessage());
        assertEquals(1L,response.getRescheduledElectionId());
        assertEquals(ElectionStatus.SCHEDULED, electionRepository.findElectionById(1L).getElectionStatus());
        assertThat(electionRepository.findElectionById(1L).getElectionCategory()).isEqualTo(LOCAL_GOVERNMENT);
    }

}