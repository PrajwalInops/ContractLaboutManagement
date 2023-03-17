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
public class LeaveTransactionReport {
	
	private String name;
	private String department;
	private String employeeId;
	private Date fromDate;
	private Date toDate;
	private String leaveTypeId;
	private double noOfDays;

}
