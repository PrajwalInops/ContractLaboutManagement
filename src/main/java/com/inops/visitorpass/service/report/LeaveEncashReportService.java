package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.Encashment;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.LeaveEncashment;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.ILeaveEncashment;

@Service("leaveEncashReportService")
public class LeaveEncashReportService implements DataExtractionService {

	private ILeaveEncashment leaveEncashment;

	public LeaveEncashReportService(ILeaveEncashment leaveEncashment) {
		super();
		this.leaveEncashment = leaveEncashment;
	}

	@Override
	public Collection<Encashment> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		Optional<List<LeaveEncashment>> leaveEncashmentList = leaveEncashment.findAllByFromDateAndToDateAndEmployeeIdIn(
				from, to, employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		return leaveEncashmentList.get().stream().map(leave -> {
			Employee employee = employeeIds.stream()
					.filter(emp -> emp.getEmployeeId().equalsIgnoreCase(leave.getEmployeeId())).findAny().orElse(null);

			return new Encashment(employee.getEmployeeName(), employee.getDepartment().getDepartmentName(),
					employee.getEmployeeId(), leave.getNoOfDays(), leave.getFromDate(), leave.getLeaveTypeId());
		}).collect(Collectors.toList());

	}

}
