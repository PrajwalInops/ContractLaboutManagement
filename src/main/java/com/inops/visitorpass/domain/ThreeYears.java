package com.inops.visitorpass.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThreeYears {
	
	private String name;
	private String department;
	//private String designation;
	//private Data doj;
	//private String qualification;
				   
	
	//private long incentiveY1;	             
	//private long incentiveY2;
	///private long incentiveY3;
	
	private long attendanceY1;
	private long attendanceY2;
	private long attendanceY3;
	
	private long lateY1;
	private long lateY2;
	private long lateY3;
	
	private long earlyY1;
	private long earlyY2;
	private long earlyY3;
	
	private long clY1;
	private long clY2;
	private long clY3;
	
	private long vlY1;
	private long vlY2;
	private long vlY3;
	
	private long lwpY1;
	private long lwpY2;
	private long lwpY3;
	
	//private String medical;
	//private String disciplin;
	//private String punishment;
	

}
