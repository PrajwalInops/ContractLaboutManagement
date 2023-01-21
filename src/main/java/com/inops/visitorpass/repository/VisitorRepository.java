package com.inops.visitorpass.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	Optional<Visitor> findByMobileNo(String mobileNo);
	
	Optional<List<Visitor>> findAllByIsApproved(Boolean isApproved);
	
	Optional<List<Visitor>> findAllByIsApprovedAndDivision(Boolean isApproved, long divisionId);
	
	Optional<List<Visitor>> findAllByDateBetween(Date start, Date end);
	
	void deleteByMobileNo(String mobileNo);
	
	long countByDateGreaterThan(Date date);
}
