package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.DailySummary;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IMuster;

@Service("dailySummary")
public class DailySummaryService implements DataExtractionService {

	private IMuster musterService;

	public DailySummaryService(IMuster musterService) {
		super();
		this.musterService = musterService;
	}

	@Override
	public Collection<DailySummary> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		return muster.get().stream().map(summary -> {
			Employee emp = employeeIds.stream()
					.filter(empId -> summary.getMusterId().getEmployeeId().equals(empId.getEmployeeId())).findAny()
					.orElse(null);
			return new DailySummary(emp.getEmployeeId(), emp.getEmployeeName(), emp.getDepartment().getDepartmentName(),
					summary.getMusterId().getAttendanceDate(), summary.getShiftId(), summary.getAttendanceId(),
					summary.getLeaveTypeId(), summary.getHoursWorked(), summary.getExtraHours(), summary.getLatePunch(),
					summary.getEarlyOut(), summary.getFirstInPunch(), summary.getLastOutPunch());
		}).collect(Collectors.toList());

	}

}
