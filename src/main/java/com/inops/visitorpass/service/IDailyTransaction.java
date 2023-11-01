package com.inops.visitorpass.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.inops.visitorpass.entity.Transaction;

public interface IDailyTransaction {

	Optional<List<Transaction>> findByAttendanceDateBetween(LocalDate start, LocalDate end);

	Optional<List<Transaction>> findByAttendanceDateBetweenAndTransactionIdEmployeeId(LocalDate start, LocalDate end,
			String employeeId);

	Optional<List<Transaction>> findAllByAttendanceDateBetweenAndTransactionIdEmployeeIdIn(LocalDate start,
			LocalDate end, List<String> employeeId);

	Optional<List<Object[]>> findMinMaxPunchedTimeByDateRange(String employeeId, LocalDate start, LocalDate end);
}
