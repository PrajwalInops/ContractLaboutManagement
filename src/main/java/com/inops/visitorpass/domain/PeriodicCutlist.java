package com.inops.visitorpass.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodicCutlist {
	private String name;
	private String department;
	private String employeeId;
	private double regularDays;
	private double vl;
	private double da;
	private double ots;
	private double otd;
	private double ibcut;
	private double cl;
	private double sl;
	private double nh;
	private double ml;
	private double osp;

}
