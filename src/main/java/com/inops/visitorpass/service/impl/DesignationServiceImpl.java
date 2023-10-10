package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Designation;
import com.inops.visitorpass.repository.DesignationRepository;
import com.inops.visitorpass.service.IDesignation;

import lombok.RequiredArgsConstructor;

@Service("designationService")
@RequiredArgsConstructor
public class DesignationServiceImpl implements IDesignation {

	private final DesignationRepository designationRepository;
	
	@Override
	public Optional<Designation> findById(String id) {
		return designationRepository.findById(id);
	}

	@Override
	public Optional<List<Designation>> findAll() {
		return Optional.of(designationRepository.findAll());
	}

	@Override
	public Designation save(Designation designation) {
		return designationRepository.save(designation);
	}

	@Override
	public void delete(Designation designation) {
		designationRepository.delete(designation);
	}

	@Override
	public void deleteAll(List<Designation> designation) {
		designationRepository.deleteAll(designation);
	}

}
