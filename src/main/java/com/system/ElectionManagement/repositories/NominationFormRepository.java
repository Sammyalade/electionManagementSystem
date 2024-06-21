package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.ElectionCategory;
import com.system.ElectionManagement.models.NominationForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface NominationFormRepository extends JpaRepository<NominationForm, Long> {




}
