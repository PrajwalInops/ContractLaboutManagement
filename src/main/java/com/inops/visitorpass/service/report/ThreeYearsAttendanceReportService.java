package com.inops.visitorpass.service.report;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.ThreeYears;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IMuster;

@Service("threeYearsAttendanceReport")
public class ThreeYearsAttendanceReportService implements DataExtractionService {

	private IMuster musterService;

	public ThreeYearsAttendanceReportService(IMuster musterService) {
		super();
		this.musterService = musterService;
	}

	@Override
	public Collection<ThreeYears> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {
		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));
		List<ThreeYears> threeYears = new ArrayList<>();
		employeeIds.forEach(emp -> {

			ThreeYears threeYear = new ThreeYears();
			threeYear.setName(emp.getEmployeeName());
			threeYear.setDepartment(emp.getDepartment().getDepartmentName());
			threeYear.setDesignation(null);
			threeYear.setDoj(null);

			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			 

			//////// Attendance
			List<Muster> yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear()))
					.collect(Collectors.toList());
			threeYear.setAttendanceY1(
					yearlyMusters.stream().filter(must -> !must.getAttendanceId().equals("AA")).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 1))
					.collect(Collectors.toList());
			threeYear.setAttendanceY2(
					yearlyMusters.stream().filter(must -> !must.getAttendanceId().equals("AA")).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 2))
					.collect(Collectors.toList());
			threeYear.setAttendanceY3(
					yearlyMusters.stream().filter(must -> !must.getAttendanceId().equals("AA")).count());

			/////////// Late
			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear()))
					.collect(Collectors.toList());
			threeYear.setLateY1(yearlyMusters.stream().filter(must -> must.getLatePunch() != 0).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 1))
					.collect(Collectors.toList());
			threeYear.setLateY2(yearlyMusters.stream().filter(must -> must.getLatePunch() != 0).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 2))
					.collect(Collectors.toList());
			threeYear.setLateY3(yearlyMusters.stream().filter(must -> must.getLatePunch() != 0).count());

			///////// Early

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear()))
					.collect(Collectors.toList());
			threeYear.setEarlyY1(yearlyMusters.stream().filter(must -> must.getEarlyOut() != 0).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 1))
					.collect(Collectors.toList());
			threeYear.setEarlyY2(yearlyMusters.stream().filter(must -> must.getEarlyOut() != 0).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 2))
					.collect(Collectors.toList());
			threeYear.setEarlyY3(yearlyMusters.stream().filter(must -> must.getEarlyOut() != 0).count());

			///////// CL

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear()))
					.collect(Collectors.toList());
			threeYear.setClY1(yearlyMusters.stream().filter(must -> must.getLeaveTypeId().equals("CC")).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 1))
					.collect(Collectors.toList());
			threeYear.setClY2(yearlyMusters.stream().filter(must -> must.getLeaveTypeId().equals("CC")).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 2))
					.collect(Collectors.toList());
			threeYear.setClY3(yearlyMusters.stream().filter(must -> must.getLeaveTypeId().equals("CC")).count());

			///////// VL

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear()))
					.collect(Collectors.toList());
			threeYear.setVlY1(yearlyMusters.stream().filter(must -> must.getLeaveTypeId().equals("VV")).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 1))
					.collect(Collectors.toList());
			threeYear.setVlY2(yearlyMusters.stream().filter(must -> must.getLeaveTypeId().equals("VV")).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 2))
					.collect(Collectors.toList());
			threeYear.setVlY3(yearlyMusters.stream().filter(must -> must.getLeaveTypeId().equals("VV")).count());

			///////// LWP

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear()))
					.collect(Collectors.toList());
			threeYear.setLwpY1(yearlyMusters.stream().filter(must -> must.getLeaveTypeId().equals("NN")).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 1))
					.collect(Collectors.toList());
			threeYear.setLwpY2(yearlyMusters.stream().filter(must -> must.getLeaveTypeId().equals("NN")).count());

			yearlyMusters = muster.get().stream()
					.filter(must -> Integer.parseInt(df.format(must.getMusterId().getAttendanceDate())) == (from.getYear() + 2))
					.collect(Collectors.toList());
			threeYear.setLwpY3(yearlyMusters.stream().filter(must -> must.getLeaveTypeId().equals("NN")).count());

			threeYears.add(threeYear);

		});

		return threeYears;
	}

}
