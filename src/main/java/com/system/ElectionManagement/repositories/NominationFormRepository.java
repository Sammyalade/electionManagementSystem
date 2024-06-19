package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.NominationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NominationFormRepository extends JpaRepository<NominationForm, Long> {
}
