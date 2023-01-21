package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.ContinousAbsenteesim;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IMuster;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("continousAbsenteesim")
public class ContinousAbsenteesimService implements DataExtractionService {

	private IMuster musterService;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	public ContinousAbsenteesimService(IMuster musterService) {
		super();
		this.musterService = musterService;
	}

	@Override
	public Collection<ContinousAbsenteesim> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {
		// TODO Auto-generated method stub

		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		Map<String, Map<String, Long>> absentees = muster.get().stream()
				.filter(must -> must.getAttendanceId().equals("AA"))
				.collect(Collectors.groupingBy(must -> must.getMusterId().getEmployeeId(),
						Collectors.groupingBy(Muster::getAttendanceId, Collectors.counting())));

		List<ContinousAbsenteesim> continousAbsenteesims = new ArrayList<>();

		absentees.entrySet().stream().forEach(att -> {
			Employee emp = employeeIds.stream().filter(empId -> att.getKey().equals(empId.getEmployeeId())).findAny()
					.orElse(null);

			continousAbsenteesims
					.add(new ContinousAbsenteesim(emp.getEmployeeName(), emp.getDepartment().getDepartmentName(),
							emp.getEmployeeId(), Date.from(from.atStartOfDay(defaultZoneId).toInstant()),
							Date.from(to.atStartOfDay(defaultZoneId).toInstant()), att.getValue().get("AA")));

		});
		log.info("Data is extracted successfully from Muster");
		return continousAbsenteesims;
	}

}
