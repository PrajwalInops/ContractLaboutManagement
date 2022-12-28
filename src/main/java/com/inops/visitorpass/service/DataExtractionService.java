package com.inops.visitorpass.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.inops.visitorpass.entity.Employee;

public interface DataExtractionService {
	
	public Collection<?> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds, String type);

}
