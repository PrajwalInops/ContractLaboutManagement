package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.LWPDetails;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IMuster;

import lombok.RequiredArgsConstructor;

@Service("lwpDetailsService")
@RequiredArgsConstructor
public class LwpDetailsService implements DataExtractionService {

	private final IMuster musterService;


	@Override
	public Collection<LWPDetails> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		List<LWPDetails> days = new ArrayList<>();
		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		employeeIds.forEach(emp -> {

			LWPDetails lWPDetails = new LWPDetails(emp.getEmployeeName(), emp.getDepartment().getDepartmentName(),
					emp.getEmployeeId(), 0);
			
			muster.get().stream()
					.filter(must -> must.getMusterId().getEmployeeId().equalsIgnoreCase(emp.getEmployeeId()))
					.collect(Collectors.toList()).forEach(mst -> {
						Double lwp = (mst.getAttendanceId().charAt(0) != 'N' ? 0.5 : 0.0)
								+ (mst.getAttendanceId().charAt(1) != 'N' ? 0.5 : 0.0);
						
						lWPDetails.setLwp(lwp);

					});
			days.add(lWPDetails);

		});

		return days;
	}

}
