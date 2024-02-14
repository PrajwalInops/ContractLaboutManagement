package com.inops.visitorpass.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.LeaveBalance;
import com.inops.visitorpass.entity.LeaveBalanceId;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, LeaveBalanceId> {

	Optional<List<LeaveBalance>> findAllByLastCreditDateBetweenAndLeaveBalanceIdEmployeeIdIn(Date start, Date end,
			List<String> employeeId);

	Optional<List<LeaveBalance>> findAllByLeaveBalanceIdEmployeeIdIn(List<String> employeeId);

}
