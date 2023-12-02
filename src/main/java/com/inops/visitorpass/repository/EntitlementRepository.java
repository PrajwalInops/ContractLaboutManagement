package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.RoleEntitlement;

public interface EntitlementRepository extends JpaRepository<RoleEntitlement, Long>{

}
