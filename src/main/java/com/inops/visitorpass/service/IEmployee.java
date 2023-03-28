package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Employee;


public interface IEmployee {

	Optional<Employee> findById(long id);

	Optional<List<Employee>> findAll();
	
	Employee save(Employee employee);
	
}
