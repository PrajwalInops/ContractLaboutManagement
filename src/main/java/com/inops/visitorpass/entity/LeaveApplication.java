package com.inops.visitorpass.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "LeaveApplication")
public class LeaveApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicationId;

	private String employeeId;

	private Date fromDate;
	private Date toDate;
	private String comments;
	private String applicationStatus;

	@OneToMany(mappedBy = "leaveApplication", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LeaveApplicationType> applicationTypes;
	
	@Transient
	private boolean editable;

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setApplicationTypes(List<LeaveApplicationType> applicationTypes) {
		this.applicationTypes = applicationTypes;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	

}
