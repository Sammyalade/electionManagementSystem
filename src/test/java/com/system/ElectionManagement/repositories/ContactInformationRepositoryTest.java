package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Address;
import com.system.ElectionManagement.models.ContactInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ContactInformationRepositoryTest {

    @Autowired
    private ContactInformationRepository contactInformationRepository;

    @Test
    void testAddVote() {
        ContactInformation contactInformation = new ContactInformation();
        contactInformationRepository.save(contactInformation);
        assertEquals(1L,contactInformationRepository.count());
    }
}
