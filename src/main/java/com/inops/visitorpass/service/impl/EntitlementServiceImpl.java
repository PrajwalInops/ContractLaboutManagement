package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Entitlement;
import com.inops.visitorpass.repository.EntitlementRepository;
import com.inops.visitorpass.service.IEntitlement;

@Service("entitlementServices")
public class EntitlementServiceImpl implements IEntitlement {

	private EntitlementRepository entitlementRepository;

	public EntitlementServiceImpl(EntitlementRepository entitlementRepository) {
		super();
		this.entitlementRepository = entitlementRepository;
	}

	@Override
	public Optional<List<Entitlement>> findAll() {
		return Optional.of(entitlementRepository.findAll());
	}

	@Override
	public Optional<Entitlement> findById(long id) {
		return entitlementRepository.findById(id);
	}

	@Override
	public Optional<Entitlement> create(Entitlement entitlement) {
		return Optional.of(entitlementRepository.save(entitlement));
	}

	@Override
	public Optional<Entitlement> update(Entitlement entitlement) {

		return Optional.of(entitlementRepository.save(entitlement));
	}

	@Override
	public void delete(long entitlementId) {
		Optional<Entitlement> entitlement = entitlementRepository.findById(entitlementId);
		entitlementRepository.delete(entitlement.get());
	}

}
