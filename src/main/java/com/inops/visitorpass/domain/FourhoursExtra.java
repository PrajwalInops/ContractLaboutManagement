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
public class FourhoursExtra {
	private String name;
	private String department;
	private String employeeId;
    private String cadre;
    private Date tdate;
    private String in1;
    private String out1;
    private String in2;
    private String out2;
    private String in3;
    private String out3;
    private String in4;
    private String out4;
	private String shift;
	private double hoursWorked;
	private double extra;
	private double late;
	private double early;
	private String attendanceId;
}
