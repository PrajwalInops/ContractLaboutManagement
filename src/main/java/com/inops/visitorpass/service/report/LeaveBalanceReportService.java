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
			
			return new Balance(leave.getEmployee().getEmployeeName(), leave.getEmployee().getDepartment().getDepartmentName(),
					leave.getEmployee().getEmployeeId(), leave.getClosingBalance(), leave.getCreditDate(),
					leave.getLeaveType().getLeaveCode());
		}).collect(Collectors.toList());

	}

}
