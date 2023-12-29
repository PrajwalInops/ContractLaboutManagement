package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.LeaveBalanceHistory;
import com.inops.visitorpass.entity.LeaveTypeEntity;
import com.inops.visitorpass.repository.LeaveBalanceHistoryRepository;
import com.inops.visitorpass.service.ILeaveBalanceHistory;

import lombok.RequiredArgsConstructor;

@Service("leaveBalanceHistoryService")
@RequiredArgsConstructor
public class LeaveBalanceHistoryService implements ILeaveBalanceHistory {

	private final LeaveBalanceHistoryRepository leaveBalanceHistoryRepository;

	@Override
	public Optional<List<LeaveBalanceHistory>> findAll() {
		return Optional.of(leaveBalanceHistoryRepository.findAll());
	}

	@Override
	public LeaveBalanceHistory save(LeaveBalanceHistory leaveBalanceHistory) {
		return leaveBalanceHistoryRepository.save(leaveBalanceHistory);
	}

	@Override
	public void delete(LeaveBalanceHistory leaveBalanceHistory) {
		leaveBalanceHistoryRepository.delete(leaveBalanceHistory);

	}

	@Override
	public void deleteAll(List<LeaveBalanceHistory> leaveBalanceHistorys) {
		leaveBalanceHistoryRepository.deleteAll(leaveBalanceHistorys);
	}

	@Override
	public List<LeaveBalanceHistory> findAllByEmployeeAndYear(Employee employee, int year) {
		return null;//leaveBalanceHistoryRepository.findAllByEmployeeAndYear(employee, year);
	}

	@Override
	public LeaveBalanceHistory findAllByEmployeeAndLeaveTypeAndYearAndMonth(Employee employee, LeaveTypeEntity leave, int year,
			int month){
		return null;//leaveBalanceHistoryRepository.findAllByEmployeeAndLeaveTypeAndYearAndMonth(employee, leave, year, month);
	}

}
