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
public class LogDeteails {

	private String name;
	private String department;
	private String employeeId;
	private String userId;
	private String moduleName;
	private String toDdate;
	private Date modifiedDate;
	private String ipAddress;

}
