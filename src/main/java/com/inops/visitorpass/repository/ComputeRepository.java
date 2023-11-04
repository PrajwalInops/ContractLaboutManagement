package com.inops.visitorpass.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.inops.visitorpass.entity.Muster;

public interface ComputeRepository extends JpaRepository<Muster, Long> {

	@Procedure(name = "ComputeAttendanceForAll")
	int autometicComputeAll(@Param("@TDf5") Date fromDate, @Param("@TDt5") Date toDate);

	@Procedure(name = "ComputeAttendanceFor")
	void ComputeAttendanceFor(@Param("@EmpCode") String employeeId, @Param("@TDf5") Date fromDate,
			@Param("@TDt5") Date toDate, @Param("@ComputeAttendanceFor") int computeOut);

	@Procedure(name = "CreateMusterFor")
	void CreateMusterFor(@Param("@Empcode") String employeeId, @Param("@Tempdate9") Date fromDate,
			@Param("@Filenum") int fileNumber, @Param("@CreateMusterFor") int musterOut);

}
