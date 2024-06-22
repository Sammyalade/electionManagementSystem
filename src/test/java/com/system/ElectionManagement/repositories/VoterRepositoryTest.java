package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Vote;
import com.system.ElectionManagement.models.Voter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoterRepositoryTest {

    @Autowired
    private VoterRepository voterRepository;

    @Test
    void testAddVote() {
        Voter voter = new Voter();
        voterRepository.save(voter);
        assertEquals(1L, voterRepository.count());
    }
}
