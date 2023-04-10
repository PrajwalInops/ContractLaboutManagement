package com.inops.visitorpass.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.repository.MusterRepository;
import com.inops.visitorpass.service.IMuster;

@Service("musterService")
public class MusterServiceImpl implements IMuster {

	private MusterRepository musterRepository;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	public MusterServiceImpl(MusterRepository musterRepository) {
		super();
		this.musterRepository = musterRepository;
	}

	@Override
	public Optional<List<Muster>> findByAttendanceDateBetween(LocalDate start, LocalDate end) {

		return musterRepository.findAllByMusterIdAttendanceDateBetween(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()));
	}

	@Override
	public Optional<List<Muster>> findAllByAttendanceDateBetweenAndEmployeeId(LocalDate start, LocalDate end,
			String employeeId) {

		return musterRepository.findAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeId(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId);
	}

	@Override
	public Optional<List<Muster>> findAllByAttendanceDateBetweenAndEmployeeId(LocalDate start, LocalDate end,
			List<String> employeeId) {
		return musterRepository.findAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdIn(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId);
	}

	@Override
	public Optional<List<Long>> countAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdIn(LocalDate start,
			LocalDate end, List<String> employeeId, long from, long to) {
		return musterRepository.countAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdIn(
				Date.from(start.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(end.atStartOfDay(defaultZoneId).toInstant()), employeeId, from, to);
	}

}
