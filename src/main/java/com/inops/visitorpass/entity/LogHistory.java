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
@Table(name = "Tblhistorylog")
public class LogHistory {


	@Id
	@Column(name = "logid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long logId;

	@Column(name = "userid")
	private String userId;

	@Column(name = "empid")
	private String employeeId;

	@Column(name = "modulename")
	private String moduleName;

	@Column(name = "tdate")
	private String toDdate;

	@Column(name = "mdate")
	private Date modifiedDate;

	@Column(name = "ip")
	private String ipAddress;

}
