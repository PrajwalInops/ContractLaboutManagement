package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.LeaveTransactionReport;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.LeaveTransactions;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.ILeaveTransactions;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Service("leaveTransactionReportService")
public class LeaveTransactionReportService implements DataExtractionService {

	private ILeaveTransactions leaveTransactionsService;

	public LeaveTransactionReportService(ILeaveTransactions leaveTransactionsService) {
		super();
		this.leaveTransactionsService = leaveTransactionsService;
	}

	@Override
	public Collection<LeaveTransactionReport> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds, String type) {

		Optional<List<LeaveTransactions>> leaveTransactions = leaveTransactionsService
				.findAllByFromDateBetweenAndEmployee(from, to,
						employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

	return	leaveTransactions.get().stream().map(leave -> {
			Employee employee = employeeIds.stream()
					.filter(emp -> emp.getEmployeeId().equalsIgnoreCase(leave.getLeaveTransactionId().getEmployeeId()))
					.findAny().orElse(null);
			return new LeaveTransactionReport(employee.getEmployeeName(), employee.getDepartment().getDepartmentName(),
					leave.getLeaveTransactionId().getEmployeeId(), leave.getLeaveTransactionId().getFromDate(),
					leave.getToDate(), leave.getLeaveTypeId(), leave.getNoOfDays());
		}).collect(Collectors.toList());
	}

}
