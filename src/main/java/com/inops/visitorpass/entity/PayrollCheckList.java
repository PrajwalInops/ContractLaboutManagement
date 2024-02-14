package com.inops.visitorpass.entity;

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
@Table(name = "Tblpayrollchecklist")
public class PayrollCheckList {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Empid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String employeeId;

	@Column(name = "Monthid")
	private int month;

	@Column(name = "Yearid")
	private int year;

	@Column(name = "Regdays")
	private float regularDays;

	@Column(name = "Reghrs")
	private float regularHours;

	@Column(name = "VL")
	private float vl;

	@Column(name = "DA")
	private float da;

	@Column(name = "Otsday")
	private float ots;

	@Column(name = "Otdday")
	private float otd;

	@Column(name = "NSA2")
	private float nsa2;

	@Column(name = "NSA3")
	private float nsa3;

	@Column(name = "IBD")
	private float ibd;

	@Column(name = "Clfday")
	private float cl;

	@Column(name = "SL")
	private float sl;

	@Column(name = "Nhday")
	private float nh;

}
