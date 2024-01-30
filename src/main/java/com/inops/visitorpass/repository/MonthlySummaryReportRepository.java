package com.inops.visitorpass.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.inops.visitorpass.entity.PayrollCheckList;

public interface MonthlySummaryReportRepository extends JpaRepository<PayrollCheckList, String> {

	@Procedure(procedureName = "InsertMonthlySummaryRecordFor")
	void monthlySummaryRecord(@Param("@EmpCode") String employeeId, @Param("@Year") String year,
			@Param("@Month") String month, @Param("@Result") int result);

	Optional<List<PayrollCheckList>> findAllByMonthAndYearAndEmployeeIdIn(int month, int year,
			 List<String> employeeId);
}
