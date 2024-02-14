package com.inops.visitorpass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblempmast")
public class Employee {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empid")
	private String employeeId;
	
	@Column(name = "empname")
	private String employeeName;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptid")
    private Department department;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cadreid")
    private Cadre cadre;
	
	@Transient
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "divisionId")
    private Division division;

}
