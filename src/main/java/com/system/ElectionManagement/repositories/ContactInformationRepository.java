package com.system.ElectionManagement.repositories;

import com.system.ElectionManagement.models.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {
}
