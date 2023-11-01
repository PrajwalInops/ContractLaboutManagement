package com.inops.visitorpass.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Transaction;
import com.inops.visitorpass.repository.DailyTransactionRepository;
import com.inops.visitorpass.service.IDailyTransaction;

@Service("dailyTransactionService")
public class DailyTransactionServiceImpl implements IDailyTransaction {

	private final DailyTransactionRepository dailyTransactionRepository;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	public DailyTransactionServiceImpl(DailyTransactionRepository dailyTransactionRepository) {
		super();
		this.dailyTransactionRepository = dailyTransactionRepository;
	}

	@Override
	public Optional<List<Transaction>> findByAttendanceDateBetween(LocalDate start, LocalDate end) {

		return dailyTransactionRepository.findByAttendanceDateBetween(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()));
	}

	@Override
	public Optional<List<Transaction>> findByAttendanceDateBetweenAndTransactionIdEmployeeId(LocalDate start,
			LocalDate end, String employeeId) {
		return dailyTransactionRepository.findByAttendanceDateBetweenAndTransactionIdEmployeeId(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId);
	}

	@Override
	public Optional<List<Transaction>> findAllByAttendanceDateBetweenAndTransactionIdEmployeeIdIn(LocalDate start,
			LocalDate end, List<String> employeeId) {
		return dailyTransactionRepository.findAllByAttendanceDateBetweenAndTransactionIdEmployeeIdIn(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId);
	}

	@Override
	public Optional<List<Object[]>> findMinMaxPunchedTimeByDateRange(String employeeId, LocalDate start,
			LocalDate end) {
		return dailyTransactionRepository.findMinMaxPunchedTimeByDateRange(employeeId,
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()));
	}

}
