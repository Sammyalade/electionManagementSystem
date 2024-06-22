package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.SystemAdministrator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SystemAdministratorRepositoryTest {

    @Autowired
    private SystemAdministratorRepository systemAdministratorRepository;

    @Test
    void testAddVote() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        systemAdministratorRepository.save(systemAdministrator);
        assertEquals(1L, systemAdministratorRepository.count());
    }
}
