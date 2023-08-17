package com.inops.visitorpass.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.CompensatoryOff;
import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.entity.Holiday;

public interface CompensatoryRepository extends JpaRepository<CompensatoryOff, Long> {
	
	Optional<List<CompensatoryOff>> findAllByDivision(Division divisionId);
}
