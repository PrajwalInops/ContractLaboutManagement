package com.inops.visitorpass.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.repository.ComputeRepository;
import com.inops.visitorpass.service.ICompute;

import lombok.RequiredArgsConstructor;

@Service("computeService")
@RequiredArgsConstructor
public class ComputeServiceImpl implements ICompute {

	private final ComputeRepository computeRepository;
	private final ApplicationContext ctx;

	public void computeAll(Date fromDate) {
		List<Employee> employees = ((Optional<List<Employee>>) ctx.getBean("getEmployees")).get();
		employees.forEach(
				employee -> computeRepository.ComputeAttendanceFor(employee.getEmployeeId(), fromDate, fromDate, 0));

	}

	@Override
	public void computeByDateAndEmployee(String employeeId, Date fromDate, Date toDate) {
		computeRepository.ComputeAttendanceFor(employeeId, fromDate, toDate, 0);
	}

	@Override
	public void computeAllByDate(Date fromDate, Date toDate) {
		computeRepository.autometicComputeAll(fromDate, toDate);
	}

	@Override
	public void createMusterByDateAndEmployee(String employeeId, Date fromDate) {
		computeRepository.CreateMusterFor(employeeId, fromDate, 0, 0);

	}

	@Override
	public void createMusterForAll(Date fromDate) {
		List<Employee> employees = ((Optional<List<Employee>>) ctx.getBean("getEmployees")).get();
		employees.forEach(employee -> computeRepository.CreateMusterFor(employee.getEmployeeId(), fromDate, 0, 0));

	}

}
