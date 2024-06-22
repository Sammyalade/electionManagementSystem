package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoteRepositoryTest {

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void testAddVote() {
        Vote vote = new Vote();
        voteRepository.save(vote);
        assertEquals(1L, voteRepository.count());
    }
}
