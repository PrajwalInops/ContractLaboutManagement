package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.repository.EmployeeRepository;
import com.inops.visitorpass.service.IEmployee;

@Service("employeeService")
public class EmployeeService implements IEmployee {

	
	private EmployeeRepository employeeRepository;
			
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Optional<Employee> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<List<Employee>> findAll() {
		return Optional.of(employeeRepository.findAll());
	}

}
