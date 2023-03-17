package com.inops.visitorpass.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
@Embeddable
public class LeaveTransactionId implements Serializable{

	@Column(name = "empid")
	private String employeeId;

	@Column(name = "Datefrom")
	private Date fromDate;
	
	@Column(name = "Fromhalfdayflag")
	private String fromHalfDayFlag;
}
