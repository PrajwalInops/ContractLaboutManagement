package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Department;
import com.inops.visitorpass.repository.DepartmentsRepository;
import com.inops.visitorpass.service.IDepartment;

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartment {

	private DepartmentsRepository departmentsRepository;
	
	
	
		
	public DepartmentServiceImpl(DepartmentsRepository departmentsRepository) {
		super();
		this.departmentsRepository = departmentsRepository;
	}

	@Override
	public Optional<Department> findById(String id) {
		
		return  departmentsRepository.findById(id);
	}

	@Override
	public Optional<List<Department>> findAll() {
		return  Optional.of(departmentsRepository.findAll());
	}

}
