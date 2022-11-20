package com.inops.visitorpass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	Optional<Visitor> findByMobileNo(String mobileNo);
	
	void deleteByMobileNo(String mobileNo);
}
