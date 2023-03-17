package com.inops.visitorpass.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.LeaveTransactions;
import com.inops.visitorpass.repository.LeaveTransactionsRepository;
import com.inops.visitorpass.service.ILeaveTransactions;

@Service("leaveTransactionsService")
public class LeaveTransactionsService implements ILeaveTransactions {

	private LeaveTransactionsRepository leaveTransactionsRepository;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	public LeaveTransactionsService(LeaveTransactionsRepository leaveTransactionsRepository) {
		super();
		this.leaveTransactionsRepository = leaveTransactionsRepository;
	}

	@Override
	public Optional<List<LeaveTransactions>> findAll() {

		return Optional.of(leaveTransactionsRepository.findAll());
	}

	@Override
	public Optional<List<LeaveTransactions>> findAllByFromDateBetweenAndEmployee(LocalDate start, LocalDate end,
			List<String> employeeId) {

		return leaveTransactionsRepository.findAllByLeaveTransactionIdFromDateBetweenAndLeaveTransactionIdEmployeeIdIn(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId);
	}

}
