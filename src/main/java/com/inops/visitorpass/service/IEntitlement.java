package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Entitlement;

public interface IEntitlement {
	Optional<List<Entitlement>> findAll();

	Optional<Entitlement> findById(long id);

	Optional<Entitlement> create(Entitlement entitlement);

	Optional<Entitlement> update(Entitlement entitlement);

	void delete(long entitlementId);
}
