package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.RoleEntitlement;

public interface IEntitlement {
	Optional<List<RoleEntitlement>> findAll();

	Optional<RoleEntitlement> findById(long id);

	Optional<RoleEntitlement> create(RoleEntitlement entitlement);

	Optional<RoleEntitlement> update(RoleEntitlement entitlement);

	void delete(long entitlementId);
	
	void deleteAll(List<Long> entitelmentIds);
}
