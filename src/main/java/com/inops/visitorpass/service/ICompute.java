package com.inops.visitorpass.service;

import java.util.Date;

public interface ICompute {

	public void computeByDateAndEmployee(String employeeId, Date fromDate, Date toDate);

	public void computeAll();

	public void computeAllByDate(Date fromDate, Date toDate);
	
	public void createMusterByDateAndEmployee(String employeeId, Date fromDate);
	
	public void createMusterForAll(Date fromDate);
}
