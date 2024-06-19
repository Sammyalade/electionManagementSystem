package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void testAddVote() {
        Address address = new Address();
        addressRepository.save(address);
        assertEquals(1L, addressRepository.count());
    }
}
