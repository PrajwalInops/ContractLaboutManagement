package com.inops.visitorpass.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinantialCutlist {
	private String name;
	private String department;
	private String employeeId;
    private String cadre;
	private double regularDays;
	private double leaveDays;
}
