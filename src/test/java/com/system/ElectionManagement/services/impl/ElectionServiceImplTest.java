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

    @Test
    @Sql(scripts = {"/db/data.sql"})
    @DisplayName("test that to view election results")
    public void testViewElectionResult() {
        Election election = getElection();
        Candidate candidate = getCandidate();
        ElectionResult electionResult = getElectionResult(election, candidate);
        election.setElectionResult(electionResult);
        electionRepository.save(election);
        ViewElectionResultRequest viewResult = new ViewElectionResultRequest();
        ViewElectionResultResponse resultResponse = electionService.viewElectionResult(viewResult);
        assertNotNull(resultResponse);
        assertThat(election.getElectionResult()).isEqualTo(electionResult);

    }

    private static ElectionResult getElectionResult(Election election, Candidate candidate) {
        ElectionResult electionResult = new ElectionResult();
        electionResult.setTotalNumberOfVoteCasted(1000L);
        electionResult.setElection(election);
        electionResult.setElectionCategory(ElectionCategory.PRESIDENTIAL);
        electionResult.setElectionStatus(ElectionStatus.CONCLUDED);
        electionResult.setCandidate(candidate);
        electionResult.setNumberOfVotesReceived(600L);
        electionResult.setElectionStartTime(LocalDateTime.of(2024, 6, 1, 8, 0));
        electionResult.setElectionEndTime(LocalDateTime.of(2024, 6, 1, 20, 0));

        electionResult.setTotalNumberOfVoteCasted(1000L);
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
        election.setElectionCategory(ElectionCategory.PRESIDENTIAL);
        return election;
    }
    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testRescheduleElection() {
        Optional<Election> optionalElection = electionRepository.findById(1L);
        Election existingElection = optionalElection.get();

        assertEquals(ElectionStatus.SCHEDULED, existingElection.getElectionStatus());

        RescheduleElectionRequest request = new RescheduleElectionRequest();
        request.setElectionId(Long.valueOf(String.valueOf(existingElection.getId())));
        LocalDateTime newStartTime = LocalDateTime.of(2025, 7, 1, 10, 0);
        request.setStartTime(newStartTime.toString());
        RescheduleElectionResponse response = electionService.rescheduleElection(request);
        assertNotNull(response);
        assertEquals("Election rescheduled successfully", response.getMessage());
        assertEquals(String.valueOf(existingElection.getId()), response.getRescheduledElectionId());
        assertEquals(newStartTime, response.getRescheduledElectionDate());
        Optional<Election> updatedOptional = electionRepository.findById(existingElection.getId());
        Election updatedElection = updatedOptional.orElseThrow(() -> new ElectionNotFoundException("Updated election not found"));

        assertEquals(newStartTime, updatedElection.getStartTime());
        assertEquals(ElectionStatus.SCHEDULED, updatedElection.getElectionStatus());

    }
    @Test
    @Sql(scripts = {"/db/newData.sql"})
    public void testRescheduleElection_InvalidDate() {
        RescheduleElectionRequest request = new RescheduleElectionRequest();
        request.setElectionId(Long.valueOf(String.valueOf(1)));
        request.setStartTime(String.valueOf(LocalDateTime.now().minusDays(1L)));

        Exception exception = assertThrows(ElectionNotFoundException.class, () -> {
            electionService.rescheduleElection(request);
        });

        assertEquals("something is wrong", exception.getMessage());
    }
    @Test
    @Sql(scripts = {"/db/data.sql"})

    public void testRescheduleElection_AlreadyStarted() {
        Optional<Election> optionalElection = electionRepository.findById(1L);
        Election existingElection = optionalElection.orElseThrow(() -> new ElectionNotFoundException("Election not found"));
        existingElection.setElectionStatus(ElectionStatus.IN_PROGRESS);
        electionRepository.save(existingElection);

        RescheduleElectionRequest request = new RescheduleElectionRequest();
        request.setElectionId(Long.valueOf(String.valueOf(existingElection.getId())));
        request.setStartTime(LocalDateTime.of(2024, 7, 1, 10, 0).toString());

        Exception exception = assertThrows(CantRescheduleElectionException.class, () -> {
            electionService.rescheduleElection(request);
        });

        assertEquals("Cannot reschedule an ongoing or completed election", exception.getMessage()); // Assuming you handle this case
    }
    @Test
    @Sql(scripts = {"/db/data.sql"})

    public void testRescheduleElection_InvalidElectionId() {
        RescheduleElectionRequest request = new RescheduleElectionRequest();
        request.setElectionId(999L);
        request.setStartTime(LocalDateTime.of(2024, 7, 1, 10, 0).toString());

        Exception exception = assertThrows(ElectionNotFoundException.class, () -> {
            electionService.rescheduleElection(request);
        });

        assertEquals("something is wrong", exception.getMessage());
}