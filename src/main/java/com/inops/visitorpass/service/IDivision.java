package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Division;

public interface IDivision {

	Optional<List<Division>> findAll();

	Division save(Division division);

	void delete(Division division);

	void deleteAll(List<Division> divisions);
}
