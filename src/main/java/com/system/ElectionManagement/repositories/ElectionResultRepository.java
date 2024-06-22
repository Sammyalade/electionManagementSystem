package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.ElectionResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionResultRepository extends JpaRepository<ElectionResult, Long> {
}
