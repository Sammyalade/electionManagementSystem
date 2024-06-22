package com.system.ElectionManagement.repositories;


import com.system.ElectionManagement.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
