package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.RoleEntitlement;
import com.inops.visitorpass.repository.RoleEntitlementRepository;
import com.inops.visitorpass.service.IRoleEntitlement;

@Service("roleEntitlementService")
public class RoleEntitlementServiceImpl implements IRoleEntitlement {

	private RoleEntitlementRepository roleEntitlementRepository;

	public RoleEntitlementServiceImpl(RoleEntitlementRepository roleEntitlementRepository) {
		super();
		this.roleEntitlementRepository = roleEntitlementRepository;
	}

	@Override
	public Optional<List<RoleEntitlement>> findAll() {

		return Optional.of(roleEntitlementRepository.findAll());
	}

	@Override
	public Optional<RoleEntitlement> findById(long id) {
		return roleEntitlementRepository.findById(id);
	}

	@Override
	public Optional<RoleEntitlement> create(RoleEntitlement roleEntitlement) {
		return Optional.of(roleEntitlementRepository.save(roleEntitlement));
	}

	@Override
	public Optional<RoleEntitlement> update(RoleEntitlement roleEntitlement) {
		return Optional.of(roleEntitlementRepository.save(roleEntitlement));
	}

	@Override
	public void delete(long roleEntitlementId) {
		Optional<RoleEntitlement> roleEntitlement = roleEntitlementRepository.findById(roleEntitlementId);
		roleEntitlementRepository.delete(roleEntitlement.get());

	}

}
