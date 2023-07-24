package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Entitlement;

public interface EntitlementRepository extends JpaRepository<Entitlement, Long>{

}
