package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Designation;

public interface IDesignation {

	Optional<Designation> findById(String id);

	Optional<List<Designation>> findAll();

	Designation save(Designation designation);

	void delete(Designation designation);

	void deleteAll(List<Designation> designation);
}
