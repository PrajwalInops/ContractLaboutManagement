package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.LeaveBalanceHistory;

public interface LeaveBalanceHistoryRepository extends JpaRepository<LeaveBalanceHistory, Boolean> {

	/*
	 * List<LeaveBalanceHistory> findAllByEmployeeAndYear( int year);
	 * 
	 * LeaveBalanceHistory findAllByEmployeeAndLeaveTypeAndYearAndMonth(Employee
	 * employee, LeaveTypeEntity leave, int year, int month);
	 */
}
