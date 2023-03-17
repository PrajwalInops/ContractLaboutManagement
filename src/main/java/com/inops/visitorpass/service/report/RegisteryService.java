package com.inops.visitorpass.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.constant.InopsConstant;
import com.inops.visitorpass.domain.AttendanceRegister;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IMuster;

@Service("registery")
public class RegisteryService implements DataExtractionService {

	private IMuster musterService;

	public RegisteryService(IMuster musterService) {
		super();
		this.musterService = musterService;
	}

	public List<AttendanceRegister> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds,
			String type) {
		Map<String, AttendanceRegister> attendanceRegistry = new HashMap<>();
		Optional<List<Muster>> muster = musterService.findAllByAttendanceDateBetweenAndEmployeeId(from, to,
				employeeIds.stream().map(Employee::getEmployeeId).collect(Collectors.toList()));

		muster.get().stream().forEach(data -> {
			Date date = data.getMusterId().getAttendanceDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			String attribute = null;
			switch (type) {
			case InopsConstant.ATTENDANCE_REGISTER:
				attribute = data.getAttendanceId();
				break;
			case InopsConstant.LATEIN_REGISTER:
				attribute = String.valueOf(data.getLatePunch());
				break;
			case InopsConstant.EARLYOUT_REGISTER:
				attribute = String.valueOf(data.getEarlyOut());
				break;
			case InopsConstant.EXTRAHOURS_REGISTER:
				attribute = String.valueOf(data.getExtraHours());
				break;
			case InopsConstant.ABSENTEESM_REGISTER:
				attribute = String.valueOf(data.getAttendanceId().contains("A") ? data.getAttendanceId() : "");
				break;
			case InopsConstant.OVERTIME_REGISTRY:
				attribute = String.valueOf("S ="+data.getSingleOt()/60+" D="+data.getDoubleOt()/60);
				break;
			case InopsConstant.LEAVE_REGISTER:
				attribute = String.valueOf(data.getLeaveTypeId().equalsIgnoreCase("00")?"":data.getLeaveTypeId());
				break;

			default:
				break;
			}

			if (attendanceRegistry.containsKey(data.getMusterId().getEmployeeId())) {
				AttendanceRegister attendanceRegister = attendanceRegistry.get(data.getMusterId().getEmployeeId());
				setRegisteryAttribure(attendanceRegister, day, attribute);
			} else {
				AttendanceRegister attendanceRegister = new AttendanceRegister();
				setNameAndDepartment(attendanceRegister, data.getMusterId().getEmployeeId(), employeeIds);
				setRegisteryAttribure(attendanceRegister, day, attribute);
				attendanceRegistry.put(data.getMusterId().getEmployeeId(), attendanceRegister);
			}

		});

		return new ArrayList(attendanceRegistry.values());
	}

	private void setNameAndDepartment(AttendanceRegister attendanceRegister, String employeeId,
			List<Employee> employeeIds) {
		Employee employee = employeeIds.stream().filter(emp -> emp.getEmployeeId().equalsIgnoreCase(employeeId))
				.findAny().orElse(null);
		attendanceRegister.setName(employeeId + " : " + employee.getEmployeeName());
		attendanceRegister.setDepartment(employee.getDepartment().getDepartmentName());
	}

	private void setRegisteryAttribure(AttendanceRegister attendanceRegister, int day, String attid) {
		switch (day) {
		case 1:
			attendanceRegister.setA1(attid);
			break;
		case 2:
			attendanceRegister.setA2(attid);
			break;
		case 3:
			attendanceRegister.setA3(attid);
			break;
		case 4:
			attendanceRegister.setA4(attid);
			break;
		case 5:
			attendanceRegister.setA5(attid);
			break;
		case 6:
			attendanceRegister.setA6(attid);
			break;
		case 7:
			attendanceRegister.setA7(attid);
			break;
		case 8:
			attendanceRegister.setA8(attid);
			break;
		case 9:
			attendanceRegister.setA9(attid);
			break;
		case 10:
			attendanceRegister.setA10(attid);
			break;
		case 11:
			attendanceRegister.setA11(attid);
			break;
		case 12:
			attendanceRegister.setA12(attid);
			break;
		case 13:
			attendanceRegister.setA13(attid);
			break;
		case 14:
			attendanceRegister.setA14(attid);
			break;
		case 15:
			attendanceRegister.setA15(attid);
			break;
		case 16:
			attendanceRegister.setA16(attid);
			break;
		case 17:
			attendanceRegister.setA17(attid);
			break;
		case 18:
			attendanceRegister.setA18(attid);
			break;
		case 19:
			attendanceRegister.setA19(attid);
			break;
		case 20:
			attendanceRegister.setA20(attid);
			break;
		case 21:
			attendanceRegister.setA21(attid);
			break;
		case 22:
			attendanceRegister.setA22(attid);
			break;
		case 23:
			attendanceRegister.setA23(attid);
			break;
		case 24:
			attendanceRegister.setA24(attid);
			break;
		case 25:
			attendanceRegister.setA25(attid);
			break;
		case 26:
			attendanceRegister.setA26(attid);
			break;
		case 27:
			attendanceRegister.setA27(attid);
			break;
		case 28:
			attendanceRegister.setA28(attid);
			break;
		case 29:
			attendanceRegister.setA29(attid);
			break;
		case 30:
			attendanceRegister.setA30(attid);
			break;
		case 31:
			attendanceRegister.setA31(attid);
			break;

		default:
			break;
		}
	}

}
