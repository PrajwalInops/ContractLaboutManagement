package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.FourhoursExtra;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IMuster;

import lombok.RequiredArgsConstructor;

@Service("fourHoursExtraService")
@RequiredArgsConstructor
public class FourHoursExtraReportService implements DataExtractionService {

	private final IMuster musterService;

	@Override
	public Collection<FourhoursExtra> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		List<FourhoursExtra> fourhoursExtras = new ArrayList<>();

		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		employeeIds.stream().forEach(employee -> {
			
			muster.get().stream().filter(must -> must.getMusterId().getEmployeeId().equals(employee.getEmployeeId()))
					.collect(Collectors.toList()).forEach(musterData -> {
						if(musterData.getExtraHours()>240)
						{   FourhoursExtra extra = new FourhoursExtra();
							extra.setShift(musterData.getShiftId());
							extra.setIn1(musterData.getFirstInPunch());
							extra.setOut1(musterData.getLastOutPunch());
							extra.setIn2("");
							extra.setOut2("");
							extra.setIn3("");
							extra.setOut3("");
							extra.setIn4("");
							extra.setOut4("");
							extra.setTdate(musterData.getMusterId().getAttendanceDate());
							extra.setHoursWorked(musterData.getHoursWorked()/60);
							extra.setEmployeeId(employee.getEmployeeId());
							extra.setDepartment(employee.getDepartment().getDepartmentName());
							extra.setCadre(employee.getCadre().getCadre());
							extra.setAttendanceId(musterData.getAttendanceId());
							extra.setName(employee.getEmployeeName());
							fourhoursExtras.add(extra);
						}
					});
			
		});

		return fourhoursExtras;
	}

}
