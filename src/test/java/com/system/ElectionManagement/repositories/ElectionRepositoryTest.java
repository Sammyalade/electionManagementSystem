package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ElectionRepositoryTest {

    @Autowired
    private ElectionRepository electionRepository;

    @Test
    void testAddVote() {
        Election election = new Election();
        electionRepository.save(election);
        assertEquals(1L, electionRepository.count());
    }
}
