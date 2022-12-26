package com.inops.visitorpass.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailySummary {

	private String employeeId;
	private String name;
	private String department;
	private Date attendanceDate;
	private String shiftId;
	private String attendanceId;
	private String leaveTypeId;
	private int hoursWorked;
	private int extraHours;
	private int latePunch;
	private int earlyOut;
	private String firstInPunch;
	private String lastOutPunch;
}
