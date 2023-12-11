package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.entity.Holiday;

public interface IHoliday {

	Optional<List<Holiday>> findAll();
	
	Optional<List<Holiday>> findAllByDivisionId(Division divisionId);
	
	Holiday save(Holiday holiday);

	void delete(Holiday holiday);

	void deleteAll(List<Holiday> holidays);
}
