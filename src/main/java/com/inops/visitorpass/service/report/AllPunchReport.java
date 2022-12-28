package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.Punch;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Transaction;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IDailyTransaction;

@Service("allPunch")
public class AllPunchReport implements DataExtractionService {

	private final IDailyTransaction dailyTransactionService;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	public AllPunchReport(IDailyTransaction dailyTransactionService) {
		super();
		this.dailyTransactionService = dailyTransactionService;
	}

	@Override
	public Collection<Punch> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds, String type) {

		Optional<List<Transaction>> dailyTransaction = dailyTransactionService
				.findAllByAttendanceDateBetweenAndTransactionIdEmployeeIdIn(from, to,
						employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		return dailyTransaction.get().stream().map(trans -> {
			Employee employee = employeeIds.stream()
					.filter(empId -> trans.getTransactionId().getEmployeeId().equals(empId.getEmployeeId())).findAny()
					.orElse(null);
			return new Punch(employee.getEmployeeName(), employee.getDepartment().getDepartmentName(),
					employee.getEmployeeId(), trans.getAttendanceDate(), trans.getTransactionId().getTransactionTime(),
					trans.getActualIOFlag());
		}).collect(Collectors.toList());

	}

}
