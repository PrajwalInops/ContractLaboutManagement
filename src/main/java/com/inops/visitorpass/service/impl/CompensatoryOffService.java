package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.CompensatoryOff;
import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.repository.CompensatoryRepository;
import com.inops.visitorpass.service.ICompensatoryOff;

import lombok.RequiredArgsConstructor;

@Service("compensatoryOffService")
@RequiredArgsConstructor
public class CompensatoryOffService implements ICompensatoryOff {

	private final CompensatoryRepository compensatoryRepository;


	@Override
	public Optional<List<CompensatoryOff>> findAll() {
		return Optional.ofNullable(compensatoryRepository.findAll());
	}

	@Override
	public Optional<List<CompensatoryOff>> findAllByDivisionId(Division division) {
		return compensatoryRepository.findAllByDivision(division);
	}

}
