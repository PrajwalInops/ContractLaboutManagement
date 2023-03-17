package com.inops.visitorpass.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.LeaveTransactionId;
import com.inops.visitorpass.entity.LeaveTransactions;

public interface LeaveTransactionsRepository extends JpaRepository<LeaveTransactions, LeaveTransactionId> {

	Optional<List<LeaveTransactions>> findAllByLeaveTransactionIdFromDateBetweenAndLeaveTransactionIdEmployeeIdIn(
			Date start, Date end, List<String> employeeId);

}
