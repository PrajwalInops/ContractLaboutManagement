package com.inops.visitorpass.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.LogHistory;

public interface ILogHistory {

	Optional<List<LogHistory>> findAll();
	
	Optional<List<LogHistory>> findAllByModifiedDateBetweenAndEmployeeIdIn(LocalDate start, LocalDate end, List<String> employeeId);

}
