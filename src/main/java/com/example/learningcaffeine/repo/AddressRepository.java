package com.example.learningcaffeine.repo;

import com.example.learningcaffeine.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {}
