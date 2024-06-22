package com.electionManagementSystem.repositories;

import com.electionManagementSystem.models.SystemAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<SystemAdministrator, Long> {
}
