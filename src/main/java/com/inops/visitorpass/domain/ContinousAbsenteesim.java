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
public class ContinousAbsenteesim {
	
	private String name;
	private String department;
	private String employeeId;
	private Date startDate;
	private Date endDate;
	private long noOfDays;

}
