package com.inops.visitorpass.service.report;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.DetailedPhysicalDays;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IMuster;

@Service("detailedPhysicalDaysReport")
public class DetailedPhysicalDaysReportService implements DataExtractionService {

	private IMuster musterService;
	SimpleDateFormat dfY = new SimpleDateFormat("yyyy");
	SimpleDateFormat dfM = new SimpleDateFormat("MM");

	public DetailedPhysicalDaysReportService(IMuster musterService) {
		super();
		this.musterService = musterService;
	}

	@Override
	public Collection<?> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds, String type) {

		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));
		List<DetailedPhysicalDays> detailedPhysicalDays = new ArrayList<>();
		employeeIds.forEach(emp -> {

			Period diff = Period.between(from.withDayOfMonth(1), to.withDayOfMonth(1));
			for (int month = 0; month < diff.getMonths() + 1; month++) {

				DetailedPhysicalDays physicalDays = new DetailedPhysicalDays();
				physicalDays.setMonth(from.getMonthValue() + month);
				physicalDays.setYear(from.getYear());
				physicalDays.setName(emp.getEmployeeName());
				physicalDays.setDepartment(emp.getDepartment().getDepartmentName());

				List<Muster> detailedMuster = muster.get().stream()
						.filter(must -> Integer.parseInt(
								dfM.format(must.getMusterId().getAttendanceDate())) == (physicalDays.getMonth())
								&& must.getMusterId().getEmployeeId().equals(emp.getEmployeeId()))
						.collect(Collectors.toList());
				physicalDays.setPhysicalDays(detailedMuster
						.stream().filter(must -> !must.getAttendanceId().equals("AA")
								&& !must.getAttendanceId().equals("HH") && !must.getAttendanceId().equals("WW"))
						.count());

				physicalDays.setDaysAbsent(
						detailedMuster.stream().filter(must -> must.getAttendanceId().equals("AA")).count());

				physicalDays
						.setLeave(detailedMuster.stream().filter(must -> !must.getLeaveTypeId().equals("00")).count());

				physicalDays
						.setLeave(detailedMuster.stream().filter(must -> must.getAttendanceId().equals("HH")).count());

				physicalDays.setShortHrs(detailedMuster.stream().filter(must -> must.getShortHrs() != null).count());
				detailedPhysicalDays.add(physicalDays);
			}

		});

		return detailedPhysicalDays;
	}

}
