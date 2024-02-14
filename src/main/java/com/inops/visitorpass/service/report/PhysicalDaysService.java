package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.PhysicalDays;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IMuster;

@Service("physicalDays")
public class PhysicalDaysService implements DataExtractionService {

	private IMuster musterService;

	public PhysicalDaysService(IMuster musterService) {
		super();
		this.musterService = musterService;
	}

	@Override
	public Collection<PhysicalDays> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		List<PhysicalDays> days = new ArrayList<>();
		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		employeeIds.forEach(emp -> {

			PhysicalDays physicalDays = new PhysicalDays(emp.getEmployeeName(), emp.getDepartment().getDepartmentName(),
					emp.getEmployeeId(), 0, 0, 0, 0, 0);
			
			muster.get().stream()
					.filter(must -> must.getMusterId().getEmployeeId().equalsIgnoreCase(emp.getEmployeeId()))
					.collect(Collectors.toList()).forEach(mst -> {
						Double presentDays = (mst.getAttendanceId().charAt(0) != 'A' ? 0.5 : 0.0)
								+ (mst.getAttendanceId().charAt(1) != 'A' ? 0.5 : 0.0);
						Double absentDays = (mst.getAttendanceId().charAt(0) == 'A' ? 0.5 : 0.0)
								+ (mst.getAttendanceId().charAt(1) == 'A' ? 0.5 : 0.0);
						Double holidys = (mst.getAttendanceId().charAt(0) == 'H' ? 0.5 : 0.0)
								+ (mst.getAttendanceId().charAt(1) == 'H' ? 0.5 : 0.0);
						Double leaves = (mst.getLeaveTypeId().charAt(0) != '0' ? 0.5 : 0.0)
								+ (mst.getLeaveTypeId().charAt(1) != '0' ? 0.5 : 0.0);

						physicalDays.setDaysAbsent(physicalDays.getDaysAbsent() + absentDays);
						physicalDays.setPhysicalDays(physicalDays.getPhysicalDays() + presentDays);
						physicalDays.setHolidays(physicalDays.getHolidays() + holidys);
						physicalDays.setLeaveAvaild(physicalDays.getLeaveAvaild() + leaves);

					});
			days.add(physicalDays);

		});

		return days;
	}

}
