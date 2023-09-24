package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.repository.DivisionRepository;
import com.inops.visitorpass.service.IDivision;

@Service("division")
public class DivisionService implements IDivision {

	private final DivisionRepository divisionRepository;

	public DivisionService(DivisionRepository divisionRepository) {
		super();
		this.divisionRepository = divisionRepository;
	}

	@Override
	public Optional<List<Division>> findAll() {
		return Optional.of(divisionRepository.findAll());
	}

	@Override
	public Division save(Division division) {
		
		return divisionRepository.save(division);
	}

	@Override
	public void delete(Division division) {
		divisionRepository.delete(division);
	}

	@Override
	public void deleteAll(List<Division> divisions) {
		divisionRepository.deleteAll(divisions);
	}

}
