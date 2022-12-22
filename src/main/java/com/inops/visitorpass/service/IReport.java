package com.inops.visitorpass.service;

import java.time.LocalDate;
import java.util.List;

import com.inops.visitorpass.entity.Employee;

public interface IReport {

	public byte[] generate(LocalDate from, LocalDate to , List<Employee> id);
}
