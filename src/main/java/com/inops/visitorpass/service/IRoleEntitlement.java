package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.RoleEntitlement;

public interface IRoleEntitlement {
	Optional<List<RoleEntitlement>> findAll();

	Optional<RoleEntitlement> findById(long id);

	Optional<RoleEntitlement> create(RoleEntitlement roleEntitlement);

	Optional<RoleEntitlement> update(RoleEntitlement roleEntitlement);

	void delete(long roleEntitlementId);
}
