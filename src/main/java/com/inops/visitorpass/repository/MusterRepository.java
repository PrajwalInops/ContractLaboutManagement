package com.inops.visitorpass.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.entity.MusterId;

public interface MusterRepository extends JpaRepository<Muster, MusterId> {

	Optional<List<Muster>> findAllByMusterIdAttendanceDateBetween(Date start, Date end);

	Optional<List<Muster>> findAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeId(Date start, Date end, String employeeId);
	
	Optional<List<Muster>> findAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdIn(Date start, Date end, List<String> employeeId);

}
