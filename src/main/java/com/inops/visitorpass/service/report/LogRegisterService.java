package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.LogDeteails;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.LogHistory;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.ILogHistory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("logRegisterService")
public class LogRegisterService implements DataExtractionService {

	private final ILogHistory logHistoryService;

	@Override
	public Collection<LogDeteails> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		Optional<List<LogHistory>> logHistory = logHistoryService.findAllByModifiedDateBetweenAndEmployeeIdIn(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		return logHistory.get().stream().map(log -> {

			Employee employee = employeeIds.stream()
					.filter(emp -> emp.getEmployeeId().equalsIgnoreCase(log.getEmployeeId())).findAny().orElse(null);

			return new LogDeteails(employee.getEmployeeName(), employee.getDepartment().getDepartmentName(),
					log.getEmployeeId(), log.getUserId(), log.getModuleName(), log.getToDdate(), log.getModifiedDate(),
					log.getIpAddress());
		}).collect(Collectors.toList());
	}

}
