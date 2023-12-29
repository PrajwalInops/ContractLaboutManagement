package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.LeaveBalanceHistory;
import com.inops.visitorpass.entity.LeaveTypeEntity;

public interface ILeaveBalanceHistory {

	Optional<List<LeaveBalanceHistory>> findAll();

	LeaveBalanceHistory save(LeaveBalanceHistory leaveBalanceHistory);

	void delete(LeaveBalanceHistory leaveBalanceHistory);

	void deleteAll(List<LeaveBalanceHistory> leaveBalanceHistorys);

	List<LeaveBalanceHistory> findAllByEmployeeAndYear(Employee employee, int year);

	LeaveBalanceHistory findAllByEmployeeAndLeaveTypeAndYearAndMonth(Employee employee, LeaveTypeEntity leave, int year,
			int month);
}
