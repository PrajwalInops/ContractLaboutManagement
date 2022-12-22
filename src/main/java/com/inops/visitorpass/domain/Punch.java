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
public class Punch {
	
	private String name;
	private String department;
	private String employeeId;
	private Date attendanceDate;
	private Date transactionTime;
	private String ioMode;
	

}
