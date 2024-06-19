package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.SystemAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAdministratorRepository extends JpaRepository<SystemAdministrator, Long> {
}
