package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.repository.EmployeeRepository;
import com.inops.visitorpass.service.IEmployee;

import lombok.RequiredArgsConstructor;

@Service("employeeService")
@RequiredArgsConstructor
public class EmployeeService implements IEmployee {

	
	private final EmployeeRepository employeeRepository;

	@Override
	public Optional<Employee> findById(String id) {		
		return employeeRepository.findById(id);
	}

	@Override
	public Optional<List<Employee>> findAll() {
		// TODO Auto-generated method stub
		return Optional.of(employeeRepository.findAll());
	}

	@Override
	public Employee save(Employee epmloyee) {
		return employeeRepository.save(epmloyee);
	}

	@Override
	public void delete(Employee epmloyee) {
		employeeRepository.delete(epmloyee);
	}

	@Override
	public void deleteAll(List<Employee> epmloyees) {
		employeeRepository.deleteAll(epmloyees);
	}
		
	
	
}
