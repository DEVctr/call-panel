package com.devctr.call_panel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devctr.call_panel.model.entity.Attendant;

@Repository
public interface AttendantRepository extends JpaRepository<Attendant, Long> {
    boolean existsByEmail(String email);
    Optional<Attendant> findByEmail(String email);
}