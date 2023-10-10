package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Employee;

public interface IEmployee {

	Optional<Employee> findById(String id);

	Optional<List<Employee>> findAll();

	Employee save(Employee epmloyee);

	void delete(Employee epmloyee);

	void deleteAll(List<Employee> epmloyee);

}
