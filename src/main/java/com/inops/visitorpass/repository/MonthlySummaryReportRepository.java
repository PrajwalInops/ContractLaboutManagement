package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.inops.visitorpass.entity.PayrollCheckList;

public interface MonthlySummaryReportRepository extends JpaRepository<PayrollCheckList, Long> {

	@Procedure(procedureName = "InsertMonthlySummaryRecordFor")
	void monthlySummaryRecord(@Param("@EmpCode") String employeeId, @Param("@Year") String year,
			@Param("@Month") String month, @Param("@Result") int result);
}
