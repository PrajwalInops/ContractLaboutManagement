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
public class DetailedPhysicalDays {

	private String name;
	private String department;
	private double shortHrs;
	private double physicalDays;
	private double daysAbsent;
	private int holidays;
	private double leave;
	private int month;
	private int year;

}
