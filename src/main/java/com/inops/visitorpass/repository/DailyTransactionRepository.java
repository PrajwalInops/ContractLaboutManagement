package com.inops.visitorpass.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inops.visitorpass.entity.Transaction;
import com.inops.visitorpass.entity.TransactionId;

public interface DailyTransactionRepository extends JpaRepository<Transaction, TransactionId> {

	Optional<List<Transaction>> findByAttendanceDateBetween(Date start, Date end);

	Optional<List<Transaction>> findByAttendanceDateBetweenAndTransactionIdEmployeeId(Date start, Date end,
			String employeeId);

	Optional<List<Transaction>> findAllByAttendanceDateBetweenAndTransactionIdEmployeeIdIn(Date start, Date end,
			List<String> employeeId);

	@Query("SELECT t.attendanceDate, MIN(t.transactionId.transactionTime), MAX(t.transactionId.transactionTime) FROM Transaction t "
			+ "WHERE t.transactionId.employeeId = :employeeId " + "AND t.attendanceDate BETWEEN :start AND :end "
			+ "GROUP BY t.attendanceDate")
	Optional<List<Object[]>> findMinMaxPunchedTimeByDateRange(@Param("employeeId") String employeeId,
			@Param("start") Date start, @Param("end") Date end);
}
