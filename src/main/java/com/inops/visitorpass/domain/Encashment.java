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
public class Encashment {
	
	private String name;
	private String department;
	private String employeeId;
    private double noOfDays;
	private Date fromDate;
	private String leaveTypeId;


}
