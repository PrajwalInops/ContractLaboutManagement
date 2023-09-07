package com.inops.visitorpass.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tblleavetransactions")
public class LeaveTransactions {
	
	@EmbeddedId
	private LeaveTransactionId leaveTransactionId;
	
	@Column(name = "Leavetypeid")
	private String leaveTypeId;
	
	@Column(name = "Dateto")
	private Date toDate;
	
	@Column(name = "Tohalfdayflag")
	private String toHalfDayFlag;
	
	@Column(name = "Noofdays")
	private Double noOfDays;
	
	@Column(name = "Nodaysupdt")
	private Double noOfDaysUpdated;
	
	@Column(name = "Applieddate")
	private Date appliedDate;
	
	@Column(name = "Remarks")
	private String remark;
	
	
	
	
	
	

}
