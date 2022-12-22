package com.inops.visitorpass.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Transaction;
import com.inops.visitorpass.entity.TransactionId;

public interface DailyTransactionRepository extends JpaRepository<Transaction, TransactionId> {

	Optional<List<Transaction>> findByAttendanceDateBetween(Date start, Date end);

	Optional<List<Transaction>> findByAttendanceDateBetweenAndTransactionIdEmployeeId(Date start, Date end,
			String employeeId);

	Optional<List<Transaction>> findAllByAttendanceDateBetweenAndTransactionIdEmployeeIdIn(Date start, Date end,
			List<String> employeeId);
}
