package com.inops.visitorpass.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class TransactionId implements Serializable{
	
	@Column(name = "empid")
	private String employeeId;

	@Column(name = "transtime")
	private LocalDateTime transactionTime;

	
	@Column(name = "ioflag")
	private String inputOutputFlag;

}
