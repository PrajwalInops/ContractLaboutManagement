package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inops.visitorpass.domain.PeriodicCutlist;
import com.inops.visitorpass.domain.TwohoursLateIn;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.entity.PayrollCheckList;
import com.inops.visitorpass.repository.MonthlySummaryReportRepository;
import com.inops.visitorpass.service.DataExtractionService;

import lombok.RequiredArgsConstructor;

@Service("periodicCutlistReportService")
@RequiredArgsConstructor
public class PeriodicCutlistReportService implements DataExtractionService {

	// private final IMuster musterService;
	// private final ILeaveBalance leaveBalanceService;
	private final MonthlySummaryReportRepository monthlySummaryReportRepository;

	@Override
	// @Transactional
	public Collection<PeriodicCutlist> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		List<PeriodicCutlist> finantialCutlists = new ArrayList<>() ;

		employeeIds.forEach(employee -> {
			monthlySummaryRecord(employee.getEmployeeId(), from);
		});

//		finantialCutlists = monthlySummaryReportRepository.findAll().stream()
//				.map(period -> new PeriodicCutlist(null, null, period.getEmployeeId(), period.getRegularDays(),
//						period.getVl(), period.getDa(), period.getOts(), period.getOtd(), period.getIbd(), period.getCl(),
//						period.getSl(), period.getNh(), 0.0, period.getNsa2()))
//				.collect(Collectors.toList());
		
		Optional<List<PayrollCheckList>> cutlistDetails = monthlySummaryReportRepository.findAllByMonthAndYearAndEmployeeIdIn(from.getMonthValue(), from.getYear() , employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));
		
		employeeIds.stream().forEach(employee -> {
			
			cutlistDetails.get().stream().filter(must -> must.getEmployeeId().equals(employee.getEmployeeId()))
					.collect(Collectors.toList()).forEach(cutlistData -> { 
						
							finantialCutlists.add(new PeriodicCutlist(null, null, cutlistData.getEmployeeId(), cutlistData.getRegularDays(),
									cutlistData.getVl(), cutlistData.getDa(), cutlistData.getOts(), cutlistData.getOtd(), cutlistData.getIbd(), cutlistData.getCl(),
									cutlistData.getSl(), cutlistData.getNh(), 0.0, cutlistData.getNsa2()) );
						
					});
			
		});
		
		
		finantialCutlists.stream().forEach(checkList->{
			Employee employee = employeeIds.stream().filter(emp->emp.getEmployeeId().equals(checkList.getEmployeeId())).findAny().get();
			checkList.setName(employee.getEmployeeName());
			checkList.setDepartment(employee.getDepartment().getDepartmentName()); 
		});

		return finantialCutlists;
	}

	@Transactional(readOnly = true)
	private void monthlySummaryRecord(String empid, LocalDate from) {
		System.out.println("Call procedure");
		monthlySummaryReportRepository.monthlySummaryRecord(empid, String.valueOf(from.getYear()),
				String.valueOf(from.getMonth().getValue()), 0);
	}

}
