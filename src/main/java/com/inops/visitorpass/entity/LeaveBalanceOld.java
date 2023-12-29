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
@Table(name = "Tblempleavebalmaster")
public class LeaveBalanceOld {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LeaveBalanceId leaveBalanceId;
		
	private double balance;
	
	@Column(name = "lastcreditdate")
	private Date lastCreditDate;


}
