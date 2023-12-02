package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.RoleEntitlement;
import com.inops.visitorpass.repository.EntitlementRepository;
import com.inops.visitorpass.service.IEntitlement;

import lombok.RequiredArgsConstructor;

@Service("entitlementService")
@RequiredArgsConstructor
public class EntitlementServiceImpl implements IEntitlement {

	private final EntitlementRepository entitlementRepository;

	@Override
	public Optional<List<RoleEntitlement>> findAll() {
		return Optional.of(entitlementRepository.findAll());
	}

	@Override
	public Optional<RoleEntitlement> findById(long id) {
		return entitlementRepository.findById(id);
	}

	@Override
	public Optional<RoleEntitlement> create(RoleEntitlement entitlement) {
		return Optional.of(entitlementRepository.save(entitlement));
	}

	@Override
	public Optional<RoleEntitlement> update(RoleEntitlement entitlement) {

		return Optional.of(entitlementRepository.save(entitlement));
	}

	@Override
	public void delete(long entitlementId) {
		Optional<RoleEntitlement> entitlement = entitlementRepository.findById(entitlementId);
		if (entitlement != null) {
			entitlementRepository.delete(entitlement.get());
		}
	}

	@Override
	public void deleteAll(List<Long> entitelmentIds) {
		List<RoleEntitlement> entitlements = entitlementRepository.findAllById(entitelmentIds);
		entitlementRepository.deleteAll(entitlements);
	}

}
