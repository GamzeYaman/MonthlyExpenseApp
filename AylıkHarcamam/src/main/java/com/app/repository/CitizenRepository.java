package com.app.repository;

import com.app.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, String> {

    Citizen findByUsername(String username);
}
