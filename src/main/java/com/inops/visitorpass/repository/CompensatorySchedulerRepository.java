package com.inops.visitorpass.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.CompensatoryOffScheduler;
import com.inops.visitorpass.entity.Division;

public interface CompensatorySchedulerRepository extends JpaRepository<CompensatoryOffScheduler, Long> {
	
	Optional<List<CompensatoryOffScheduler>> findAllByDivision(Division divisionId);
}
