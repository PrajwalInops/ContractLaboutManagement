package com.inops.visitorpass.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "Tbldailytransactions")
public class Transaction {
	
	@EmbeddedId
	private TransactionId transactionId;
	
	@Column(name = "actualioflag")
	private String actualIOFlag;
	
	@Column(name = "Opflag")
	private String oPFlag;
	
	@Column(name = "Punchedtime")
	private LocalDateTime punchedTime;
	
	@Column(name = "Attendancedate")
	private Date attendanceDate;
	
	@Column(name = "Boutpasshrs")
	private int bOutPassHrs;
	
	@Column(name = "Remarks")
	private String remarks;
	
	@Column(name = "Badgereaderno")
	private int badgeReaderNo;
	
	@Column(name = "Deleted")
	private String deleted;
	
	@Column(name = "Reasoncode")
	private String reasonCode;
	
	@Transient
	@Column(name = "Opremarks")
	private String oPRemarks;

}
