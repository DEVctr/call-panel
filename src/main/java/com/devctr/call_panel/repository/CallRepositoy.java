package com.devctr.call_panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devctr.call_panel.model.entity.Call;

@Repository
public interface CallRepositoy extends JpaRepository<Call, Long> {
}