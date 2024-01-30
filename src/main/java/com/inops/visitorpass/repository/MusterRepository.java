package com.inops.visitorpass.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.entity.MusterId;

public interface MusterRepository extends JpaRepository<Muster, MusterId> {

	Optional<List<Muster>> findAllByMusterIdAttendanceDateBetween(Date start, Date end);

	Optional<List<Muster>> findAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeId(Date start, Date end,
			String employeeId);

	Optional<List<Muster>> findAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdIn(Date start, Date end,
			List<String> employeeId);

	@Query("select count(m) from Muster AS m where m.leaveTypeId ='NN' and m.musterId.attendanceDate between :start and :end and m.musterId.employeeId in (:employeeId) group by m.musterId.employeeId having count(*) between :from and :to")
	Optional<List<Long>> countAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdIn(@Param("start") Date start,
			@Param("end") Date end, @Param("employeeId") List<String> employeeId, @Param("from") long from,
			@Param("to") long to);
	
    Optional<List<Muster>> findAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdInAndLatePunchGreaterThan(
           Date start, Date end, List<String> employeeId, int minLate);
	    
	  
}
