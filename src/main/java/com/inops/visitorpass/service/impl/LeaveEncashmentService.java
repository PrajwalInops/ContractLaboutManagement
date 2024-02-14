package com.inops.visitorpass.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.LeaveBalance;
import com.inops.visitorpass.entity.LeaveEncashment;
import com.inops.visitorpass.repository.LeaveBalanceRepository;
import com.inops.visitorpass.repository.LeaveEncashRepository;
import com.inops.visitorpass.service.ILeaveBalance;
import com.inops.visitorpass.service.ILeaveEncashment;

@Service("leaveEncashmentService")
public class LeaveEncashmentService implements ILeaveEncashment {

	private LeaveEncashRepository leaveEncashRepository;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	public LeaveEncashmentService(LeaveEncashRepository leaveEncashRepository) {
		super();
		this.leaveEncashRepository = leaveEncashRepository;
	}

	@Override
	public Optional<List<LeaveEncashment>> findAllByFromDateAndToDateAndEmployeeIdIn(LocalDate start, LocalDate end,
			List<String> employeeId) {
		return leaveEncashRepository.findAllByFromDateBetweenAndEmployeeIdIn(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId);
	}

}
