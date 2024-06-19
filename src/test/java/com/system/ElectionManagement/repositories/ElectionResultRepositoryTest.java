package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Address;
import com.system.ElectionManagement.models.ElectionResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ElectionResultRepositoryTest {

    @Autowired
    private ElectionResultRepository electionResultRepository;

    @Test
    void testAddVote() {
        ElectionResult electionResult = new ElectionResult();
        electionResultRepository.save(electionResult);
        assertEquals(1L, electionResultRepository.count());
    }
}
