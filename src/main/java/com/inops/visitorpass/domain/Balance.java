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
public class Balance {
	
	private String name;
	private String department;
	private String employeeId;
    private double balance;
	private Date lastCreditDate;
	private String leaveTypeId;


}
