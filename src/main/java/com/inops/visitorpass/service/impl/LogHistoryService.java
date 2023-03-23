package com.inops.visitorpass.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.LogHistory;
import com.inops.visitorpass.repository.LogHistoryRepository;
import com.inops.visitorpass.service.ILogHistory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("logHistoryService")
public class LogHistoryService implements ILogHistory {

	private final LogHistoryRepository logHistoryRepository;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	@Override
	public Optional<List<LogHistory>> findAllByModifiedDateBetweenAndEmployeeIdIn(LocalDate start, LocalDate end,
			List<String> employeeId) {
		return logHistoryRepository.findAllByModifiedDateBetweenAndEmployeeIdIn(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId);
	}

	@Override
	public Optional<List<LogHistory>> findAll() {
		// TODO Auto-generated method stub
		return Optional.of(logHistoryRepository.findAll());
	}

}
