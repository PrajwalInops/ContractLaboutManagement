package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Department;
import com.inops.visitorpass.repository.DepartmentsRepository;
import com.inops.visitorpass.service.IDepartment;

import lombok.RequiredArgsConstructor;

@Service("departmentService")
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartment {

	private final DepartmentsRepository departmentsRepository;

	@Override
	public Optional<Department> findById(String id) {

		return departmentsRepository.findById(id);
	}

	@Override
	public Optional<List<Department>> findAll() {
		return Optional.of(departmentsRepository.findAll());
	}

	@Override
	public Department save(Department department) {
		return departmentsRepository.save(department);
	}

	@Override
	public void delete(Department department) {
		departmentsRepository.delete(department);
	}

	@Override
	public void deleteAll(List<Department> departments) {
		departmentsRepository.deleteAll(departments);
	}

}
