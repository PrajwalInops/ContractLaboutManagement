package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.CompensatoryOff;

public interface CompensatoryRepository extends JpaRepository<CompensatoryOff, Long> {
	
}
