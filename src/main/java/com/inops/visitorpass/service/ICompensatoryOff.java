package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.CompensatoryOff;
import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.entity.Holiday;

public interface ICompensatoryOff {

	Optional<List<CompensatoryOff>> findAll();

	CompensatoryOff save(CompensatoryOff compensatoryOff);

	void delete(CompensatoryOff compensatoryOff);

	void deleteAll(List<CompensatoryOff> compensatoryOffs);
}
