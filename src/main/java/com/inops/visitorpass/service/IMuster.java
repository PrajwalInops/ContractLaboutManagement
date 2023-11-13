package com.inops.visitorpass.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Muster;

public interface IMuster {

	Optional<List<Muster>> findByAttendanceDateBetween(LocalDate start, LocalDate end);

	Optional<List<Muster>> findAllByAttendanceDateBetweenAndEmployeeId(LocalDate start, LocalDate end,
			String employeeId);

	Optional<List<Muster>> findAllByAttendanceDateBetweenAndEmployeeId(LocalDate start, LocalDate end,
			List<String> employeeId);

	Optional<List<Long>> countAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdIn(LocalDate start, LocalDate end,
			List<String> employeeId, long from, long to);

	Optional<List<Object[]>> countAllPresentDaysByEmployeeId(String employeeId, int year);

}
