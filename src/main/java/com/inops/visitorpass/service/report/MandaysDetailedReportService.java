package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.FinantialCutlist;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IMuster;

import lombok.RequiredArgsConstructor;

@Service("mandaysDetailedService")
@RequiredArgsConstructor
public class MandaysDetailedReportService implements DataExtractionService {

	private final IMuster musterService;

	@Override
	public Collection<FinantialCutlist> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		List<FinantialCutlist> finantialCutlists = new ArrayList<>();

		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		employeeIds.stream().forEach(employee -> {
			FinantialCutlist cutlist = new FinantialCutlist(employee.getEmployeeName(),
					employee.getDepartment().getDepartmentName(), employee.getEmployeeId(),
					employee.getCadre().getCadre(), 0, 0);
			muster.get().stream().filter(must -> must.getMusterId().getEmployeeId().equals(employee.getEmployeeId()))
					.collect(Collectors.toList()).forEach(musterData -> {
						if (musterData.getHoursWorked() != 0) {
							cutlist.setRegularDays(cutlist.getRegularDays() + 1);
						}						
					});
			finantialCutlists.add(cutlist);
		});

		return finantialCutlists;
	}

}
