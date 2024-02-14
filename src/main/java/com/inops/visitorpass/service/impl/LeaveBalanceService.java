package com.inops.visitorpass.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.LeaveBalance;
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

		return leaveBalanceRepository.findAllByLastCreditDateBetweenAndLeaveBalanceIdEmployeeIdIn(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId);
	}

	@Override
	public Optional<List<LeaveBalance>> findAllByEmployeeIdIn(List<String> employeeId) {
		// TODO Auto-generated method stub
		return leaveBalanceRepository.findAllByLeaveBalanceIdEmployeeIdIn(employeeId);
	}

}
