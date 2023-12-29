package com.inops.visitorpass.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.LeaveBalance;
import com.inops.visitorpass.entity.LeaveTypeEntity;

public interface ILeaveBalance {

	Optional<List<LeaveBalance>> findAll();
	
	Optional<List<LeaveBalance>> findAllByEmployeeIds(LocalDate start, LocalDate end, List<String> employeeId);
	
	Optional<List<LeaveBalance>> findAllByEmployeeId(LocalDate start, LocalDate end, String employeeId);
	
	Optional<LeaveBalance> findByEmployeeAndLeaveType(Employee employee, LeaveTypeEntity leave);
	
	LeaveBalance save(LeaveBalance leaveBalance);
	
	void delete(LeaveBalance leaveBalance);
	
	void deleteAll(List<LeaveBalance> leaveBalances);
}
