package com.inops.visitorpass.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	Optional<Visitor> findByMobileNo(String mobileNo);
	
	Optional<List<Visitor>> findAllByIsApproved(Boolean mobileNo);
	
	void deleteByMobileNo(String mobileNo);
}
