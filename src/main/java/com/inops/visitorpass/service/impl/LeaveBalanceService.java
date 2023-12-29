package com.inops.visitorpass.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.LeaveBalance;
import com.inops.visitorpass.entity.LeaveBalanceOld;
import com.inops.visitorpass.entity.LeaveTypeEntity;
import com.inops.visitorpass.repository.LeaveBalanceRepository;
import com.inops.visitorpass.service.ILeaveBalance;

@Service("leaveBalanceService")
public class LeaveBalanceService implements ILeaveBalance {

	private LeaveBalanceRepository leaveBalanceRepository;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	public LeaveBalanceService(LeaveBalanceRepository leaveBalanceRepository) {
		super();
		this.leaveBalanceRepository = leaveBalanceRepository;
	}

	@Override
	public Optional<List<LeaveBalance>> findAllByEmployeeIds(LocalDate start, LocalDate end, List<String> employeeId) {

		return leaveBalanceRepository.findAllByCreditDateBetweenAndEmployeeEmployeeIdIn(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId);
	}

	@Override
	public Optional<List<LeaveBalance>> findAll() {

		return Optional.of(leaveBalanceRepository.findAll());
	}

	@Override
	public Optional<List<LeaveBalance>> findAllByEmployeeId(LocalDate start, LocalDate end, String employeeId) {
		return leaveBalanceRepository.findAllByCreditDateBetweenAndEmployeeEmployeeId(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId);
	}

	@Override
	public Optional<LeaveBalance> findByEmployeeAndLeaveType(Employee employee, LeaveTypeEntity leave) {

		return leaveBalanceRepository.findByEmployeeAndLeaveType(employee, leave);
	}

	@Override
	public LeaveBalance save(LeaveBalance leaveBalance) {
		return leaveBalanceRepository.save(leaveBalance);
	}

	@Override
	public void delete(LeaveBalance leaveBalance) {
		
		leaveBalanceRepository.delete(leaveBalance);
	}

	@Override
	public void deleteAll(List<LeaveBalance> leaveBalances) {
		leaveBalanceRepository.deleteAll(leaveBalances);		
	}

}
