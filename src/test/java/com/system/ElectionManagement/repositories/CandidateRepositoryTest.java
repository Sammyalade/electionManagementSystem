package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Candidate;
import com.system.ElectionManagement.models.Election;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CandidateRepositoryTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    void testAddVote() {
        Candidate candidate = new Candidate();
        candidateRepository.save(candidate);
        assertEquals(1L, candidateRepository.count());
    }
}
