package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Department;

public interface IDepartment {
	
	Optional<Department> findById(String id);

	Optional<List<Department>> findAll();

}
