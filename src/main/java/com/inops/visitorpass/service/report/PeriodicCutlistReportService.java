package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.domain.PeriodicCutlist;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.LeaveBalance;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.ILeaveBalance;
import com.inops.visitorpass.service.IMuster;

import lombok.RequiredArgsConstructor;

@Service("periodicCutlistReportService")
@RequiredArgsConstructor
public class PeriodicCutlistReportService implements DataExtractionService {

	private final IMuster musterService;
	private final ILeaveBalance leaveBalanceService;

	@Override
	public Collection<PeriodicCutlist> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {

		List<PeriodicCutlist> finantialCutlists = new ArrayList<>();

		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		List<LeaveBalance> leaveBalances = leaveBalanceService.findAllByEmployeeIds(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList())).get();

		employeeIds.stream().forEach(employee -> {
			PeriodicCutlist cutlist = new PeriodicCutlist(employee.getEmployeeName(),
					employee.getDepartment().getDepartmentName(), employee.getEmployeeId(), 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0);
			muster.get().stream().filter(must -> must.getMusterId().getEmployeeId().equals(employee.getEmployeeId()))
					.collect(Collectors.toList()).forEach(musterData -> {

						if (musterData.getHoursWorked() != 0) {
							cutlist.setRegularDays(cutlist.getRegularDays() + musterData.getHoursWorked());
						}						

						if (musterData.getSingleOt() != 0) {
							cutlist.setOts(cutlist.getOts() + musterData.getSingleOt());
						}
						if (musterData.getDoubleOt() != 0) {
							cutlist.setOtd(cutlist.getOtd() + musterData.getDoubleOt());
						}
						
						if (!musterData.getAttendanceId().equals("AA") || !musterData.getLeaveTypeId().equals("00")) {
							cutlist.setDa(cutlist.getDa() + 1);
						}	

					});
			List<LeaveBalance> balance = leaveBalances.stream()
					.filter(bal -> bal.getLeaveBalanceId().getEmployeeId().equals(employee.getEmployeeId()))
					.collect(Collectors.toList());
			cutlist.setRegularDays((cutlist.getRegularDays() / 60) / 8);
			cutlist.setOts((cutlist.getOts() / 60) / 8);
			cutlist.setOtd((cutlist.getOtd() / 60) / 8);
			
			if(!balance.isEmpty()) {
			
			LeaveBalance leaveBal = balance.stream()
					.filter(bal -> bal.getLeaveBalanceId().getLeaveTypeId().equals("CL")).findAny().orElse(null);
			cutlist.setCl(leaveBal.getBalance());
			
			leaveBal = balance.stream()
					.filter(bal -> bal.getLeaveBalanceId().getLeaveTypeId().equals("VL")).findAny().orElse(null);
			cutlist.setVl(leaveBal.getBalance());
			
			leaveBal = balance.stream()
					.filter(bal -> bal.getLeaveBalanceId().getLeaveTypeId().equals("SL")).findAny().orElse(null);
			cutlist.setSl(leaveBal.getBalance());
			
			leaveBal = balance.stream()
					.filter(bal -> bal.getLeaveBalanceId().getLeaveTypeId().equals("ML")).findAny().orElse(null);
			cutlist.setMl(leaveBal.getBalance());
			
			cutlist.setNh(cutlist.getRegularDays() / 60);
			}
			finantialCutlists.add(cutlist);
		});

		return finantialCutlists;
	}

}
