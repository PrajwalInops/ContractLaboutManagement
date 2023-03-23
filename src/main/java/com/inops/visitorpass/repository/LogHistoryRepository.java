package com.inops.visitorpass.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.LogHistory;

public interface LogHistoryRepository extends JpaRepository<LogHistory, Long> {

	Optional<List<LogHistory>> findAllByModifiedDateBetweenAndEmployeeIdIn(Date start, Date end,
			List<String> employeeId);

}
