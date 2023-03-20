package com.inops.visitorpass.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consolidated {

	private String department;
	private String shift;
	private long totalCount;
	private double present;
	private double absent;
	private double leaveAvaild;
}
