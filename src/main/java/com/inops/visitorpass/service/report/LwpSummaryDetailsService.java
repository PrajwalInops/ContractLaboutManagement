package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.LWPSummary;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IMuster;

import lombok.RequiredArgsConstructor;

@Service("lwpSummaryDetailsService")
@RequiredArgsConstructor
public class LwpSummaryDetailsService implements DataExtractionService {

	protected final IMuster musterService;

	@Override
	public Collection<LWPSummary> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		List<LWPSummary> days = new ArrayList<>();

		LWPSummary lWPDetails = new LWPSummary();
		Optional<List<Long>> muster = musterService.countAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdIn(from,
				to, employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()), 0, 16);
		int count = muster.get().size();
		lWPDetails.setLwpDays("Less then 15 Days ");
		lWPDetails.setLwp(count);
		days.add(lWPDetails);

		lWPDetails = new LWPSummary();
		muster = musterService.countAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdIn(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()), 15, 30);
		count = muster.get().size();
		lWPDetails.setLwpDays("16 - 30 Days ");
		lWPDetails.setLwp(count);
		days.add(lWPDetails);

		lWPDetails = new LWPSummary();
		muster = musterService.countAllByMusterIdAttendanceDateBetweenAndMusterIdEmployeeIdIn(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()), 31, 45);
		count = muster.get().size();
		lWPDetails.setLwpDays("31 - 45 Days ");
		lWPDetails.setLwp(count);
		days.add(lWPDetails);

		return days;
	}

}
