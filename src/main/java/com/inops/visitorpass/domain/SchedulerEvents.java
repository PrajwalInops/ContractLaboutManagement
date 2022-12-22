package com.inops.visitorpass.domain;

import org.apache.poi.ss.formula.functions.T;
import org.primefaces.model.DefaultScheduleEvent;

public class SchedulerEvents extends DefaultScheduleEvent<T> {

	private String phoneNumber;
	private String visitorName;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

}
