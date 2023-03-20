package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.Balance;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.LeaveBalance;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.ILeaveBalance;

@Service("leaveBalanceReportService")
public class LeaveBalanceReportService implements DataExtractionService {

	private ILeaveBalance leaveBalanceService;

	public LeaveBalanceReportService(ILeaveBalance leaveBalanceService) {
		super();
		this.leaveBalanceService = leaveBalanceService;
	}

	@Override
	public Collection<Balance> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds, String type) {

		Optional<List<LeaveBalance>> leaveBalances = leaveBalanceService.findAllByEmployeeIds(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		return leaveBalances.get().stream().map(leave -> {
			Employee employee = employeeIds.stream()
					.filter(emp -> emp.getEmployeeId().equalsIgnoreCase(leave.getLeaveBalanceId().getEmployeeId()))
					.findAny().orElse(null);

			return new Balance(employee.getEmployeeName(), employee.getDepartment().getDepartmentName(),
					leave.getLeaveBalanceId().getEmployeeId(), leave.getBalance(), leave.getLastCreditDate(),
					leave.getLeaveBalanceId().getLeaveTypeId());
		}).collect(Collectors.toList());

	}

}
