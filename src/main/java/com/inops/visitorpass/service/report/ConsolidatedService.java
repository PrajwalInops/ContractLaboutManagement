package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.Consolidated;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.entity.Shift;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IDepartment;
import com.inops.visitorpass.service.IMuster;
import com.inops.visitorpass.service.IShift;

import lombok.RequiredArgsConstructor;

@Service("consolidatedService")
@RequiredArgsConstructor
public class ConsolidatedService implements DataExtractionService {

	private final IMuster musterService;
	private final IShift shiftService;
	private final IDepartment departmentService;

	@Override
	public Collection<Consolidated> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		List<Consolidated> finalConsolidated = new ArrayList<>();

		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		Optional<List<Shift>> shifts = shiftService.findAll();
		departmentService.findAll().get().forEach(dept -> {

			shifts.get().forEach(shift -> {
				Consolidated consolidated = new Consolidated();
				consolidated.setDepartment(dept.getDepartmentName());
				long totalCount = muster.get().stream().filter(must -> must.getShiftId() != null)
						.filter(must -> must.getShiftId().equalsIgnoreCase(shift.getShiftId())).count();

				consolidated.setTotalCount(totalCount);
				consolidated.setShift(shift.getShiftId());
				muster.get().stream().filter(must -> must.getShiftId() != null)
						.filter(must -> must.getShiftId().equalsIgnoreCase(shift.getShiftId())).forEach(must -> {

							Double present = must.getFirstInPunch() != null ? 1.0 : 0.0;

							Double absent = must.getFirstInPunch() == null ? 1.0 : 0.0;

							Double leaves = must.getLeaveTypeId().charAt(0) != '0'
									|| must.getLeaveTypeId().charAt(1) != '0' ? 1.0 : 0.0;

							consolidated.setPresent(consolidated.getPresent() + present);
							consolidated.setAbsent(consolidated.getAbsent() + absent);
							consolidated.setLeaveAvaild(consolidated.getLeaveAvaild() + leaves);

						});
				finalConsolidated.add(consolidated);
			});

		});

		return finalConsolidated;
	}

}
