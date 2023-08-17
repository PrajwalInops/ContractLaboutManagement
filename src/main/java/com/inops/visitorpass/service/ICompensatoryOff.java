package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.CompensatoryOff;
import com.inops.visitorpass.entity.Division;

public interface ICompensatoryOff {

	Optional<List<CompensatoryOff>> findAll();

	Optional<List<CompensatoryOff>> findAllByDivisionId(Division divisionId);
}
