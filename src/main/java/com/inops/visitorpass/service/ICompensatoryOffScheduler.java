package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.CompensatoryOffScheduler;
import com.inops.visitorpass.entity.Division;

public interface ICompensatoryOffScheduler {

	Optional<List<CompensatoryOffScheduler>> findAll();

	Optional<List<CompensatoryOffScheduler>> findAllByDivisionId(Division divisionId);
}
