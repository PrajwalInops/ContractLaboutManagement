package com.inops.visitorpass.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.entity.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	
	Optional<List<Holiday>> findAllByDivision(Division divisionId);
}
