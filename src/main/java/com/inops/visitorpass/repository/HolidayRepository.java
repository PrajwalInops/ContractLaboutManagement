package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	
}
