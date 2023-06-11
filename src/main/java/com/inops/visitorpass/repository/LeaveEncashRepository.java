package com.inops.visitorpass.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.LeaveEncashment;

public interface LeaveEncashRepository extends JpaRepository<LeaveEncashment, Long> {

	Optional<List<LeaveEncashment>> findAllByFromDateBetweenAndEmployeeIdIn(Date start, Date end,
			List<String> employeeId);

}
