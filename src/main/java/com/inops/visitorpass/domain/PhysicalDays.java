package com.inops.visitorpass.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalDays {
	
	private String name;
	private String department;
	private String employeeId;
	private double physicalDays;
	private double daysAbsent;
	private double holidays;
	private double shortHours;
	private double leaveAvaild;	
}
