package com.inops.visitorpass.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Getter
//@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LeaveApplicationType")
public class LeaveApplicationType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicationTypeId;

	private String leaveTypeCode;

	@ManyToOne
	@JoinColumn(name = "applicationId")
	private LeaveApplication leaveApplication;

	private Date appliedDate;
	private float dayType;
	private String comments;
	private String status;
		
	public void setApplicationTypeId(long applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}

	public void setLeaveApplication(LeaveApplication leaveApplication) {
		this.leaveApplication = leaveApplication;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public void setDayType(float dayType) {
		this.dayType = dayType;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
