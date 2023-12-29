package com.inops.visitorpass.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.LeaveBalance;
import com.inops.visitorpass.entity.LeaveTypeEntity;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

	Optional<List<LeaveBalance>> findAllByCreditDateBetweenAndEmployeeEmployeeIdIn(Date start, Date end,
			List<String> employeeId);

	Optional<List<LeaveBalance>> findAllByCreditDateBetweenAndEmployeeEmployeeId(Date start, Date end, String employee);

	Optional<LeaveBalance> findByEmployeeAndLeaveType(Employee employee, LeaveTypeEntity leave);

}
