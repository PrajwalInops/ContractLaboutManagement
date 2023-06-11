package com.inops.visitorpass.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Tblleaveencashment")
public class LeaveEncashment {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "encashid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long encashId;
	
	@Column(name = "empid")
	private String employeeId;
		
	@Column(name = "Leavetypeid")
	private String leaveTypeId;
	
	@Column(name = "Datefrom")
	private Date fromDate;
	
	@Column(name = "Dateto")
	private Date toDate;
	
	@Column(name = "Fromhalfdayflag")
	private boolean fromHalfDayFlag;
	
	@Column(name = "Tohalfdayflag")
	private boolean toHalfDayFlag;
	
	@Column(name = "Noofdays")
	private double noOfDays;
	
	@Column(name = "Nodaysupdt")
	private double noOfDaysUpdated;
	


}
