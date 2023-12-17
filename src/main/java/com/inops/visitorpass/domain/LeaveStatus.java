package com.inops.visitorpass.domain;

public enum LeaveStatus {

	APPROVE("Approve"),REJECT("Reject");
	
	private String status;

	private LeaveStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
