package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Election;
import com.system.ElectionManagement.models.NominationForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NominationFormRepositoryTest {

    @Autowired
    private NominationFormRepository nominationFormRepository;

    @Test
    void testAddVote() {
        NominationForm nominationForm = new NominationForm();
        nominationFormRepository.save(nominationForm);
        assertEquals(1L, nominationFormRepository.count());
    }
}
